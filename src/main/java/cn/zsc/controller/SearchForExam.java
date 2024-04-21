package cn.zsc.controller;

import cn.zsc.entity.Course;
import cn.zsc.entity.CourseClass;
import cn.zsc.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam/search")
public class SearchForExam {

   @Autowired
   private TermService termService;

   @GetMapping("/terms")
   public List<String> getAllTermId() {
      return termService.getAllTermIds();
   }

   @Autowired
   private MajorService majorService;

   @GetMapping("/majors")
   public List<String> getAllMajorNames() {
      return majorService.getAllMajorNames();
   }


   @GetMapping("/singlemajor/{majorName}")
   public ResponseEntity<Integer> findMajorIdByName(@PathVariable String majorName) {
      try {
         Integer majorId = majorService.findMajorIdByName(majorName);

         if (majorId != null) {
            return ResponseEntity.ok(majorId);
         } else {
            // Major not found
            return ResponseEntity.notFound().build();
         }
      } catch (Exception e) {
         // Handle exceptions and return an appropriate response
         return ResponseEntity.internalServerError().build();
      }
   }

   @Autowired
   private School_ClassService schoolClassService;

   @GetMapping("/classnames/{majorId}")
   public ResponseEntity<List<String>> getClassNamesByMajorId(@PathVariable int majorId) {
      try {
         List<String> classNames = schoolClassService.findClassNamesByMajorId(majorId);
         return ResponseEntity.ok(classNames);
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }
   @Autowired
   private CourseClassService courseClassService;

   @GetMapping("/findCourseIds/{termId}/{className}")
   public ResponseEntity<List<Integer>> findCourseIdsByTermAndClassName(
           @PathVariable String termId,
           @PathVariable String className
   ) {
      try {
         List<Integer> courseIds = courseClassService.findCourseIdsByTermAndClassName(termId, className);
         return ResponseEntity.ok(courseIds);
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }


   @Autowired
   private CourseService courseService;

   @GetMapping("/course/{courseId}")
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

}
