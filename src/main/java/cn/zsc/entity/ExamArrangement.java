package cn.zsc.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class ExamArrangement {
   private int exam_id;
   private String exam_major_name;
   private String exam_course;
   private String exam_class_name;
   private String exam_classroom_name;
   private String exam_term;
   private String exam_date;
   private String exam_main_teacher;
   private String exam_sub_teacher1;
   private String exam_sub_teacher2;
   private String exam_form;
}
