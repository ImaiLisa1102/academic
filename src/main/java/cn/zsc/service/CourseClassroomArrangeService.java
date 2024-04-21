package cn.zsc.service;

import cn.zsc.controller.ExamArrangementController;
import cn.zsc.entity.ClassroomUsage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CourseClassroomArrangeService {
   @Autowired
   private StudentService studentService;

   @Autowired
   private ClassroomService classroomService;

   @Autowired
   private ClassroomUsageService classroomUsageService;

   private static final Logger logger = LoggerFactory.getLogger(ExamArrangementController.class);

   // 处理教室分配并返回结果的主要函数
   public List<Map<String, Object>> allocateClassroomsAndReturnResults(
           List<Map<String, Object>> mergedCourseList,
           List<String> dateList,
           List<Map<String, Object>> simplifiedClassrooms) {

      String[] timeSlots = {"8:30-10:00", "10:30-12:00", "13:30-15:00", "15:30-17:00"};
      List<Map<String, Object>> results = new ArrayList<>();
      Map<String, Set<String>> classDateAllocations = new HashMap<>();

      Collections.sort(mergedCourseList, (course1, course2) -> Long.compare((Long) course2.get("studentCount"), (Long) course1.get("studentCount")));
      Collections.sort(simplifiedClassrooms, (c1, c2) -> Integer.compare((Integer) c1.get("capacity"), (Integer) c2.get("capacity")));

      for (Map<String, Object> course : mergedCourseList) {
         String class_name = (String) course.get("class_name");

         classDateAllocations.computeIfAbsent(class_name, k -> new HashSet<>());
         Set<String> classAllocatedDates = classDateAllocations.get(class_name);

         for (String dateString : dateList) {
            if (!classAllocatedDates.contains(dateString)) {
               for (String timeSlot : timeSlots) {
                  String dateTime = dateString + " " + timeSlot;
                  if (classroomUsageService.getClassroomUsageWithClassName(class_name, dateTime) == null) {
                     // 尝试为课程分配教室
                     String allocationResult = attemptAllocateClassrooms(course, dateTime, simplifiedClassrooms, results);
                     if (allocationResult.equals("multiple")) {
                        classAllocatedDates.add(dateString);
                        break;
                     } else if (allocationResult.equals("single")) {
                        return assignClassroomToBothClasses(results);
                     }
                  }
               }
               if (classAllocatedDates.contains(dateString)) {
                  break;
               }
            }
         }
      }

      return separateClassroomAllocation(results);
   }

   // 尝试为课程分配教室
   private String attemptAllocateClassrooms(Map<String, Object> course, String dateTime, List<Map<String, Object>> classrooms, List<Map<String, Object>> results) {
      long studentCount = (Long) course.get("studentCount");

      List<String> bestClassrooms = new ArrayList<>();
      long bestCapacity = 0;

      // 查找最适合的两个教室组合
      for (int i = 0; i < classrooms.size() - 1; i++) {
         for (int j = i + 1; j < classrooms.size(); j++) {
            Map<String, Object> firstClassroom = classrooms.get(i);
            Map<String, Object> secondClassroom = classrooms.get(j);

            long firstCapacity = ((Integer) firstClassroom.get("capacity")).longValue();
            long secondCapacity = ((Integer) secondClassroom.get("capacity")).longValue();
            long totalCapacity = firstCapacity + secondCapacity;

            if (totalCapacity >= studentCount && (totalCapacity - studentCount < 10)) {
               if (classroomUsageService.getClassroomUsageWithClassroomName((String) firstClassroom.get("name"), dateTime) == null &&
                       classroomUsageService.getClassroomUsageWithClassroomName((String) secondClassroom.get("name"), dateTime) == null) {
                  if (bestCapacity == 0 || totalCapacity < bestCapacity) {
                     bestCapacity = totalCapacity;
                     bestClassrooms.clear();
                     bestClassrooms.add((String) firstClassroom.get("name"));
                     bestClassrooms.add((String) secondClassroom.get("name"));
                  }
               }
            }
         }
      }

      // 根据找到的最优教室组合安排考试
      if (!bestClassrooms.isEmpty()) {
         scheduleClassroomUsage(String.join("/", bestClassrooms), dateTime, course, results);
         return "multiple"; // 分配多个教室的情况
      }

      // 如果两个教室组合不可用，尝试单个教室排考
      for (Map<String, Object> classroom : classrooms) {
         long capacity = ((Integer) classroom.get("capacity")).longValue();
         if (classroomUsageService.getClassroomUsageWithClassroomName((String) classroom.get("name"), dateTime) == null && capacity >= studentCount) {
            scheduleClassroomUsage((String) classroom.get("name"), dateTime, course, results);
            return "single"; // 分配单个教室的情况
         }
      }

      // 如果单个和两个教室都无法满足排考需求，返回false
      return "false";
   }

   // 安排教室使用并将结果添加到列表
   private void scheduleClassroomUsage(String classroomName, String dateTime, Map<String, Object> course, List<Map<String, Object>> results) {
      ClassroomUsage classroomUsage = new ClassroomUsage();
      classroomUsage.setClassroom_name(classroomName);
      classroomUsage.setExamDate(dateTime);
      classroomUsage.setClass_name((String) course.get("class_name"));
      classroomUsageService.insertClassroomUsage(classroomUsage);

      // 构建并将结果添加到列表中
      Map<String, Object> result = new HashMap<>();
      result.put("course_id", course.get("course_id"));
      result.put("studentCount", course.get("studentCount"));
      result.put("major", course.get("major"));
      result.put("course_name", course.get("course_name"));
      result.put("year", course.get("year"));
      result.put("examType", course.get("examType"));
      result.put("term", course.get("term"));
      result.put("examTime", course.get("examTime"));
      result.put("examDate", dateTime);
      result.put("classroomName", classroomName);
      result.put("class_name", course.get("class_name"));
      results.add(result);
   }

   // 将日期转换为格式化字符串
   public String convertDateToFormattedString(String inputDate) {
      try {
         SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat formattedFormat = new SimpleDateFormat("yyyy年MM月dd日");

         Date date = originalFormat.parse(inputDate);
         return formattedFormat.format(date);
      } catch (ParseException e) {
         e.printStackTrace(); // 适当处理异常
         return null;
      }
   }

   // 将分配结果中的教室名称和班级名称拆分开
   public List<Map<String, Object>> separateClassroomAllocation(List<Map<String, Object>> allocationResults) {
      List<Map<String, Object>> separatedAllocationResults = new ArrayList<>();

      for (Map<String, Object> allocation : allocationResults) {
         // 将原始教室名称拆分为两个教室
         String[] classroomNames = ((String) allocation.get("classroomName")).split("/");
         String[] classNames = ((String) allocation.get("class_name")).split("/");

         // 获取两个班级的人数
         long studentCountClass1 = studentService.getStudentCountInClass(classNames[0]);
         long studentCountClass2 = studentService.getStudentCountInClass(classNames[1]);

         // 获取两个教室的容量
         int capacityClassroom1 = classroomService.getCapacity(classroomNames[0]);
         int capacityClassroom2 = classroomService.getCapacity(classroomNames[1]);

         // 确保class1总是人数多的班级，并且classroom1是对应人数多的班级能容纳的教室
         if (studentCountClass1 < studentCountClass2 || capacityClassroom1 < capacityClassroom2) {
            // 如果class2的人数更多，或者classroom2的容量更大，交换班级名和教室
            String tempClass = classNames[0];
            classNames[0] = classNames[1];
            classNames[1] = tempClass;

            long tempCount = studentCountClass1;
            studentCountClass1 = studentCountClass2;
            studentCountClass2 = tempCount;

            int tempCapacity = capacityClassroom1;
            capacityClassroom1 = capacityClassroom2;
            capacityClassroom2 = tempCapacity;

            String tempClassroom = classroomNames[0];
            classroomNames[0] = classroomNames[1];
            classroomNames[1] = tempClassroom;
         }

         // 为每个班级创建单独的map，并写入对应教室
         Map<String, Object> allocationClass1 = new HashMap<>(allocation);
         allocationClass1.put("classroomName", classroomNames[0]);
         allocationClass1.put("class_name", classNames[0]);
         allocationClass1.put("studentCount", studentCountClass1);

         Map<String, Object> allocationClass2 = new HashMap<>(allocation);
         allocationClass2.put("classroomName", classroomNames[1]);
         allocationClass2.put("class_name", classNames[1]);
         allocationClass2.put("studentCount", studentCountClass2);

         // 将分开后的班级和教室添加到新的list中
         separatedAllocationResults.add(allocationClass1);
         separatedAllocationResults.add(allocationClass2);
      }

      return separatedAllocationResults;
   }

   // 将教室分配给两个班级
   public List<Map<String, Object>> assignClassroomToBothClasses(List<Map<String, Object>> allocationResults) {
      List<Map<String, Object>> updatedAllocationResults = new ArrayList<>();

      for (Map<String, Object> allocation : allocationResults) {
         // 现在假设allocation中的教室可以坐下两个班级
         String classroomName = (String) allocation.get("classroomName");
         String[] classNames = ((String) allocation.get("class_name")).split("/");

         // 获取两个班级的人数
         long studentCountClass1 = studentService.getStudentCountInClass(classNames[0]);
         long studentCountClass2 = studentService.getStudentCountInClass(classNames[1]);

         // 创建两个班级的allocation映射，但教室名称相同
         Map<String, Object> allocationClass1 = new HashMap<>(allocation);
         allocationClass1.put("classroomName", classroomName);
         allocationClass1.put("class_name", classNames[0]);
         allocationClass1.put("studentCount", studentCountClass1);

         Map<String, Object> allocationClass2 = new HashMap<>(allocation);
         allocationClass2.put("classroomName", classroomName);
         allocationClass2.put("class_name", classNames[1]);
         allocationClass2.put("studentCount", studentCountClass2);

         // 将处理后的两个班级的allocation添加到结果列表中
         updatedAllocationResults.add(allocationClass1);
         updatedAllocationResults.add(allocationClass2);
      }

      return updatedAllocationResults;
   }
}
