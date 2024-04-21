package cn.zsc.controller;

import cn.zsc.entity.*;
import cn.zsc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/excelimport")
public class ExcelImportController {
   @Autowired
   private CourseService courseService;

   @Autowired
   private ManagerService managerService;

   @Autowired
   private StudentService studentService;

   @Autowired
   private TeacherService teacherService;

   @Autowired
   private ClassroomService classroomService;

   @Autowired
   private CollegeService collegeService;

   @Autowired
   private MajorService majorService;

   @Autowired
   private School_ClassService school_classService;

   @Autowired
   private TermService termService;

   @Autowired
   private BCryptPasswordEncoder passwordEncoder;

   @PostMapping("/student")
   public ResponseEntity<Void> importStudents(@RequestBody List<Student> students) {
      for(Student student : students) {
         String encryptedPassword = passwordEncoder.encode(student.getStu_password());
         student.setStu_password(encryptedPassword);
      }
      studentService.batchInsertStudents(students);
      return ResponseEntity.ok().build();
   }

   @PostMapping("/manager")
   public ResponseEntity<?> batchInsertManagers(@RequestBody List<Manager> managers) {
      for(Manager manager : managers) {
         String encryptedPassword = passwordEncoder.encode(manager.getMan_password());
         manager.setMan_password(encryptedPassword);
      }
      managerService.batchInsertManagers(managers);
      return ResponseEntity.ok("Managers inserted successfully");
   }

   @PostMapping("/teacher")
   public ResponseEntity<?> batchInsertTeachers(@RequestBody List<Teacher> teachers) {
      for(Teacher teacher : teachers) {
         String encryptedPassword = passwordEncoder.encode(teacher.getTea_password());
         teacher.setTea_password(encryptedPassword);
      }
      teacherService.batchInsertTeachers(teachers);
      return ResponseEntity.ok("Teachers inserted successfully");
   }

   @PostMapping("/classroom")
   public ResponseEntity<?> batchInsertClassrooms(@RequestBody List<Classroom> classrooms) {
      classroomService.batchInsertClassrooms(classrooms);
      return ResponseEntity.ok("Classrooms inserted successfully");
   }

   @PostMapping("/college")
   public ResponseEntity<?> batchInsertColleges(@RequestBody List<College> colleges) {
      collegeService.batchInsertColleges(colleges);
      return ResponseEntity.ok("Colleges inserted successfully");
   }

   @PostMapping("/course")
   public ResponseEntity<?> batchInsertCourses(@RequestBody List<Course> courses) {
      courseService.batchInsertCourses(courses);
      return ResponseEntity.ok("Courses inserted successfully");
   }

   @PostMapping("/major")
   public ResponseEntity<?> batchInsertMajors(@RequestBody List<Major> majors) {
      majorService.batchInsertMajors(majors);
      return ResponseEntity.ok("Majors inserted successfully");
   }


   @PostMapping("/class")
   public ResponseEntity<?> batchInsertClasses(@RequestBody List<School_Class> classes) {
      school_classService.batchInsertClasses(classes);
      return ResponseEntity.ok("Classes inserted successfully");
   }



   @PostMapping("/term")
   public ResponseEntity<?> batchInsertTerms(@RequestBody List<Term> terms) {
      termService.batchInsertTerms(terms);
      return ResponseEntity.ok("Terms inserted successfully");
   }
}
