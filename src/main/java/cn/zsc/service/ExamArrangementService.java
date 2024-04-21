package cn.zsc.service;

import cn.zsc.entity.ExamArrangement;
import cn.zsc.mapper.ExamArrangementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamArrangementService {
   @Autowired
   private ExamArrangementMapper examArrangementMapper;

   public void insertExamArrangement(ExamArrangement examArrangement) {
      examArrangementMapper.insertExamArrangement(examArrangement);
   }

   public List<ExamArrangement> getExamByDateAndMainTeacher(String examDate, String teacherName) {
      return examArrangementMapper.getExamByDateAndMainTeacher(examDate, teacherName);
   }

   public List<ExamArrangement> getExamByDateAndSubTeacher1(String examDate, String teacherName) {
      return examArrangementMapper.getExamByDateAndSubTeacher1(examDate, teacherName);
   }

   public List<ExamArrangement> getExamByDateAndSubTeacher2(String examDate, String teacherName) {
      return examArrangementMapper.getExamByDateAndSubTeacher2(examDate, teacherName);
   }

   public List<ExamArrangement> findByClassName(String examClassName) {
      return examArrangementMapper.findByClassName(examClassName);
   }

   public List<ExamArrangement> getSupervisedExamsByTeacherIdentity(String teacherIdentity) {
      List<ExamArrangement> exams = new ArrayList<>();
      exams.addAll(examArrangementMapper.findMainSupervisionByTeacherIdentity(teacherIdentity));
      exams.addAll(examArrangementMapper.findAssistantSupervisionByTeacherIdentity(teacherIdentity));
      return exams;
   }

   public List<ExamArrangement> getExamArrangementsByMajor(String examMajorName) {
      return examArrangementMapper.findByMajorName(examMajorName);
   }

}
