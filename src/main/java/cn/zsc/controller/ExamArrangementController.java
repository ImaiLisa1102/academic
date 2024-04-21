package cn.zsc.controller;

import cn.zsc.entity.Classroom;
import cn.zsc.entity.ClassroomUsage;
import cn.zsc.entity.ExamArrangement;
import cn.zsc.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exam")
public class ExamArrangementController {

   @Autowired
   private StudentService studentService;

   @Autowired
   private ClassroomService classroomService;

   @Autowired
   private TeacherService teacherService;

   @Autowired
   private School_ClassService school_classService;

   @Autowired
   private CourseClassroomArrangeService courseClassroomArrangeService;

   @Autowired
   private ExamAllocationService examAllocationService;

   @Autowired
   private ExamArrangementService examArrangementService;

   @Autowired
   private ExamTeacherArrangeService examTeacherArrangeService;

   private static final Logger logger = LoggerFactory.getLogger(ExamArrangementController.class);

   // 安排考试
   @PostMapping("/arrangement")
   public ResponseEntity<String> arrangeExams(@RequestBody Map<String, Object> requestData){
      // 从前端获取信息
      List<Map<String, Object>> courseInfoArray = (List<Map<String, Object>>) requestData.get("courseInfoArray");
      List<String> dateArray = (List<String>) requestData.get("dateArray");

      Map<String, Map<String, Object>> mergedCourseInfo = new HashMap<>();
      Map<String, Integer> classNameToYearMap = new HashMap<>();

      // 1. 遍历courseInfoArray获取学生人数和年份并存放到courseInfo中
      for (Map<String, Object> courseInfo : courseInfoArray) {
         String className = (String) courseInfo.get("class_name");

         // 获取年份信息
         Integer year = classNameToYearMap.computeIfAbsent(className, k -> school_classService.getClassYearByClassName(k));
         // 获取学生人数信息
         Long studentCount = studentService.getStudentCountInClass(className);

         // 将获取到的学生人数和年份存入courseInfo
         courseInfo.put("studentCount", studentCount);
         courseInfo.put("year", year);
      }

      // 2. 根据`course_id`和班级前缀合并相同的课程，累加学生人数
      for (Map<String, Object> courseInfo : courseInfoArray) {
         String className = (String) courseInfo.get("class_name");
         String classPrefix = className.substring(0, className.lastIndexOf('-'));
         String courseKey = courseInfo.get("course_id") + "-" + classPrefix;
         Integer year = (Integer) courseInfo.get("year");
         Long studentCount = (Long) courseInfo.get("studentCount");
         // 合并学生人数
         mergedCourseInfo.compute(courseKey, (key, existingCourseInfo) -> {
            if (existingCourseInfo == null) {
               existingCourseInfo = new HashMap<>(courseInfo);
            } else {
               // 如果已存在，累加学生人数，合并班级名
               existingCourseInfo.put("studentCount", (Long) existingCourseInfo.get("studentCount") + studentCount);
               String existingClassName = (String) existingCourseInfo.get("class_name");
               existingClassName = existingClassName.concat("/" + className);
               existingCourseInfo.put("class_name", existingClassName);
            }
            return existingCourseInfo;
         });
      }

      // 3. 创建一个新列表，用于保存合并后的课程数据
      List<Map<String, Object>> mergedCourseList = new ArrayList<>(mergedCourseInfo.values());

      // 获取所有教室名称和容量信息
      List<Classroom> classrooms = classroomService.getAllAvailableClassrooms();
      // 简化教室，
      List<Map<String, Object>> simplifiedClassrooms = new ArrayList<>();
      for (Classroom classroom : classrooms) {
         Map<String, Object> simplifiedInfo = new HashMap<>();
         simplifiedInfo.put("name", classroom.getClassroom_name());
         simplifiedInfo.put("capacity", classroom.getClassroom_capacity());
         simplifiedClassrooms.add(simplifiedInfo);
      }

      // 处理完成的课程信息表格
      logger.info("处理后的结果 ： mergedCourseList={}", mergedCourseList);
      // 打印日志
      logger.info("处理完成:\n  dateArray={},  \n simplifiedClassrooms={}", dateArray, simplifiedClassrooms);

      List<Map<String, Object>> allocationResults = courseClassroomArrangeService.allocateClassroomsAndReturnResults(mergedCourseList, dateArray, simplifiedClassrooms);
      logger.info("排座结果： {}", allocationResults);

      // 老师安排方法
      List<Map<String, Object>> resultList = examTeacherArrangeService.assignTeachersToExaminations(allocationResults, teacherService.getAllTeacherNames());
      logger.info("resultList: {}", resultList);

      for (Map<String, Object> examInfo : resultList) {
         ExamArrangement examArrangement = new ExamArrangement();

         // 添加日志打印，确认 'course_name' 键的值
         logger.info("Course name from examInfo: {}", examInfo.get("course_name")); // 添加这行进行调试

         examArrangement.setExam_major_name((String) examInfo.get("major"));
         examArrangement.setExam_course((String) examInfo.get("course_name")); // 确保这里的值不是null
         examArrangement.setExam_class_name((String) examInfo.get("class_name"));
         examArrangement.setExam_classroom_name((String) examInfo.get("classroomName")); // 确保这里的值不是null
         examArrangement.setExam_term((String) examInfo.get("term"));

         // 制造一个日期时间字符串
         String examDateTime = (String) examInfo.get("examDate");
         examArrangement.setExam_date(examDateTime);
         examArrangement.setExam_main_teacher((String) examInfo.get("mainTeacher"));
         examArrangement.setExam_sub_teacher1((String) examInfo.get("subTeacher1"));
         examArrangement.setExam_sub_teacher2(examInfo.get("subTeacher2") == null ? null : (String) examInfo.get("subTeacher2"));
         examArrangement.setExam_form((String) examInfo.get("examType"));

         examArrangementService.insertExamArrangement(examArrangement);
         
         
      }
      // 返回排考成功的信息
      String successMessage = "考试安排成功";
      return new ResponseEntity<>(successMessage, HttpStatus.OK);
   }
   
}
