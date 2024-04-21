package cn.zsc.controller;

import cn.zsc.entity.*;
import cn.zsc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchSingleElement {

   @Autowired
   ManagerService managerService;

   @ResponseBody
   @GetMapping("/find/manager/{man_num}")
   public ResponseEntity<Manager> getManagerByManagerNumber(@PathVariable int man_num) {
      try {
         Manager manager = managerService.findManagerByManagerNumber(man_num);
         if (manager != null) {
            return ResponseEntity.ok(manager);
         } else {
            // Manager not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

   @Autowired
   private TeacherService teacherService;

   @GetMapping("/find/teacher/{tea_num}")
   public ResponseEntity<Teacher> getTeacherByTeacherNumber(@PathVariable int tea_num) {
      try {
         Teacher teacher = teacherService.findTeacherByTeacherNumber(tea_num);

         if (teacher != null) {
            return ResponseEntity.ok(teacher);
         } else {
            // Teacher not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

   @Autowired
   private StudentService studentService;

   @GetMapping("/find/student/{stu_num}")
   public ResponseEntity<Student> getStudentByStudentNumber(@PathVariable int stu_num) {
      try {
         Student student = studentService.findStudentByStudentNumber(stu_num);

         if (student != null) {
            return ResponseEntity.ok(student);
         } else {
            // Student not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }



   @Autowired
   private CourseService courseService;

   @GetMapping("/find/course/{courseId}")
   public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
      try {
         Course course = courseService.findCourseById(courseId);

         if (course != null) {
            return ResponseEntity.ok(course);
         } else {
            // Course not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }


   @Autowired
   private ClassroomService classroomService;

   @GetMapping("/find/classroom/{classroom_Id}")
   public ResponseEntity<Classroom> SearchClassroomById(@PathVariable int classroom_Id) {
      try {
         Classroom classroom = classroomService.findClassroomById(classroom_Id);

         if (classroom != null) {
            return ResponseEntity.ok(classroom);
         } else {
            // College not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }



   @Autowired
   private CollegeService collegeService;

   @GetMapping("/find/college/{collegeId}")
   public ResponseEntity<College> searchCollegeById(@PathVariable int collegeId) {
      try {
         College college = collegeService.findCollegeById(collegeId);

         if (college != null) {
            return ResponseEntity.ok(college);
         } else {
            // College not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

   @Autowired
   private MajorService majorService;

   @GetMapping("/find/major/{majorId}")
   public ResponseEntity<Major> getMajorById(@PathVariable int majorId) {
      try {
         Major major = majorService.findMajorById(majorId);

         if (major != null) {
            return ResponseEntity.ok(major);
         } else {
            // Major not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

   @Autowired
   private TermService termService;

   @GetMapping("/find/term/{termId}")
   public ResponseEntity<Term> getTermById(@PathVariable String termId) {
      try {
         Term term = termService.findTermById(termId);

         if (term != null) {
            return ResponseEntity.ok(term);
         } else {
            // Term not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

   @Autowired
   private School_ClassService school_ClassService;

   @GetMapping("/find/class/{className}")
   public ResponseEntity<School_Class> getSchoolClassByName(@PathVariable String className) {
      try {
         School_Class schoolClass = school_ClassService.findSchoolClassByName(className);

         if (schoolClass != null) {
            return ResponseEntity.ok(schoolClass);
         } else {
            // School Class not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }




}

