package cn.zsc.controller;

import cn.zsc.entity.School_Class;
import cn.zsc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {
   @Autowired
   private ClassroomService classroomService;

   @DeleteMapping("/delete/classroom/{classroom_id}")
   public void deleteClassroom(@PathVariable Long classroom_id) {
      classroomService.deleteClassroom(classroom_id);
   }

   @Autowired
   private CourseService courseService;

   @DeleteMapping("/delete/course/{course_id}")
   public void deleteCourse(@PathVariable Long course_id) {
      courseService.deleteCourse(course_id);
   }

   @Autowired
   private ManagerService managerService;

   @DeleteMapping("/delete/manager/{man_num}")
   public void deleteManager(@PathVariable Long man_num) {
      managerService.deleteManager  (man_num);
   }

   @Autowired
   private StudentService studentService;

   @DeleteMapping("/delete/student/{stu_num}")
   public void deleteStudent(@PathVariable Long stu_num) {
      studentService.deleteStudent(stu_num);
   }
   @Autowired
   private TeacherService teacherService;

   @DeleteMapping("/delete/teacher/{tea_num}")
   public void deleteTeacher(@PathVariable Long tea_num) {
      teacherService.deleteTeacher(tea_num);
   }

   @Autowired
   private CollegeService collegeService;

   @DeleteMapping("/delete/college/{collegeId}")
   public void deleteCollege(@PathVariable int collegeId) {
      collegeService.deleteCollege(collegeId);
   }
   @Autowired
   private School_ClassService school_classService;

   @DeleteMapping("/delete/class/{classId}")
   public void deleteClass(@PathVariable int classId) {
      school_classService.deleteClass(classId);
   }
   @Autowired
   private TermService termService;

   @DeleteMapping("/delete/term/{termId}")
   public void deleteTerm(@PathVariable String termId) {
      termService.deleteTerm(termId);
   }
   @Autowired
   private MajorService majorService;

   @DeleteMapping("/delete/major/{majorId}")
   public void deleteMajor(@PathVariable int majorId) {
      majorService.deleteMajor(majorId);
   }
}
