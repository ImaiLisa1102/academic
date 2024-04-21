package cn.zsc.controller;

import cn.zsc.entity.*;
import cn.zsc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/singleimport")
public class SingleImportController {
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
   public ResponseEntity<String> addStudent(@RequestBody Student student) {
      try {
         String encryptedPassword = passwordEncoder.encode(student.getStu_password());
         student.setStu_password(encryptedPassword);

         studentService.insertStudent(student);
         return new ResponseEntity<>("Student added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         return new ResponseEntity<>("Failed to add the student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/teacher")
   public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher) {
      try {
         String encryptedPassword = passwordEncoder.encode(teacher.getTea_password());
         teacher.setTea_password(encryptedPassword);

         teacherService.insertTeacher(teacher);
         return new ResponseEntity<>("Teacher added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         return new ResponseEntity<>("Failed to add the teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/manager")
   public ResponseEntity<String> addManager(@RequestBody Manager manager) {
      try {
         String encryptedPassword = passwordEncoder.encode(manager.getMan_password());
         manager.setMan_password(encryptedPassword);

         managerService.insertManager(manager);
         return new ResponseEntity<>("Manager added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         return new ResponseEntity<>("Failed to add the manager: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/classroom")
   public ResponseEntity<String> addClassroom(@RequestBody Classroom classroom) {
      try {
         classroomService.insertClassroom(classroom);
         return new ResponseEntity<>("Classroom added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         // Log the exception here using a logger
         return new ResponseEntity<>("Failed to add the classroom: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/college")
   public ResponseEntity<String> addCollege(@RequestBody College college) {
      try {
         collegeService.insertCollege(college);
         return new ResponseEntity<>("College added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         // Log the exception here using a logger
         return new ResponseEntity<>("Failed to add the college: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/major")
   public ResponseEntity<String> addMajor(@RequestBody Major major) {
      try {
         majorService.insertMajor(major);
         return new ResponseEntity<>("Major added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         // Log the exception here using a logger
         return new ResponseEntity<>("Failed to add the major: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/class")
   public ResponseEntity<String> addSchool_Class(@RequestBody School_Class school_class) {
      try {
         school_classService.insertClass(school_class);
         return new ResponseEntity<>("School_Class added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         // Log the exception here using a logger
         return new ResponseEntity<>("Failed to add the school_class: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/term")
   public ResponseEntity<String> addTerm(@RequestBody Term term) {
      try {
         termService.insertTerm(term);
         return new ResponseEntity<>("Term added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         // Log the exception here using a logger
         return new ResponseEntity<>("Failed to add the term: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/course")
   public ResponseEntity<String> addCourse(@RequestBody Course course) {
      try {
         courseService.insertCourse(course);
         return new ResponseEntity<>("Course added successfully.", HttpStatus.CREATED);
      } catch (Exception e) {
         // Log the exception here using a logger
         return new ResponseEntity<>("Failed to add the course: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
}