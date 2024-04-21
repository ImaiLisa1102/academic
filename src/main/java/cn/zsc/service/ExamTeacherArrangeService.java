package cn.zsc.service;

import cn.zsc.entity.ExamArrangement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamTeacherArrangeService {
   @Autowired
   private ExamArrangementService examArrangementService;

   private Map<String, Integer> teacherInvigilationsCount; // 跟踪教师的监考次数

   public ExamTeacherArrangeService() {
      this.teacherInvigilationsCount = new HashMap<>();
   }

   // 为每个考试分配监考教师
   public List<Map<String, Object>> assignTeachersToExaminations(List<Map<String, Object>> allocationResults,
                                                                 List<String> teacherList) {
      resetTeacherInvigilationsCount(teacherList); // 重置监考次数

      for (Map<String, Object> allocation : allocationResults) {
         Number studentCountNumber = (Number) allocation.get("studentCount");
         int studentCount = studentCountNumber.intValue();
         // 根据学生人数确定需要的监考教师数
         int numTeachersNeeded = studentCount > 30 ? 3 : 2;

         List<String> availableTeachers = new ArrayList<>(teacherList);
         for (int i = 0; i < numTeachersNeeded; i++) {
            String assignedTeacher = randomlySelectTeacher(availableTeachers, allocation.get("examDate").toString());
            if (assignedTeacher != null) {
               String key = (i == 0) ? "mainTeacher" : (i == 1) ? "subTeacher1" : "subTeacher2";
               allocation.put(key, assignedTeacher);
               availableTeachers.remove(assignedTeacher);
               teacherInvigilationsCount.put(assignedTeacher,
                       teacherInvigilationsCount.getOrDefault(assignedTeacher, 0) + 1);
            } else {
               // Handle the case when a teacher is not found, perhaps throw an exception or log a warning
            }
         }

         if (studentCount <= 30) {
            allocation.put("subTeacher2", null); // 如果学生人数小于等于30，资深教师为空
         }
      }

      return allocationResults; // 返回包含分配结果的列表
   }

   private String randomlySelectTeacher(List<String> teacherList, String examDate) {
      Collections.shuffle(teacherList);
      return teacherList.stream()
              .filter(teacher -> isTeacherAvailableForExamDate(teacher, examDate))
              .findFirst()
              .orElse(null); // 若找不到可用教师则返回null
   }

   private boolean isTeacherAvailableForExamDate(String teacherName, String examDate) {
      // 获取主监考教师在该日期的所有监考安排
      List<ExamArrangement> mainTeacherExams = examArrangementService.getExamByDateAndMainTeacher(examDate, teacherName);
      if (!mainTeacherExams.isEmpty()) {
         return false; // 如果作为主监考有安排，则不空闲
      }

      // 获取副监考1教师在该日期的所有监考安排
      List<ExamArrangement> subTeacher1Exams = examArrangementService.getExamByDateAndSubTeacher1(examDate, teacherName);
      if (!subTeacher1Exams.isEmpty()) {
         return false; // 如果作为副监考1有安排，则不空闲
      }

      // 获取副监考2教师在该日期的所有监考安排
      List<ExamArrangement> subTeacher2Exams = examArrangementService.getExamByDateAndSubTeacher2(examDate, teacherName);
      if (!subTeacher2Exams.isEmpty()) {
         return false; // 如果作为副监考2有安排，则不空闲
      }

      return true; // 如果作为主监考和副监考都没有安排，则空闲
   }

   // 重置教师监考次数
   private void resetTeacherInvigilationsCount(List<String> teacherList) {
      teacherInvigilationsCount.clear();
      for (String teacher : teacherList) {
         teacherInvigilationsCount.put(teacher, 0);
      }
   }

   // 其他方法...
}
