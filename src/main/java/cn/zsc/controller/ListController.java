package cn.zsc.controller;

import cn.zsc.entity.*;
import cn.zsc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/list")
public class ListController {
   @Autowired
   private StudentService studentService;

   @Autowired
   private ManagerService managerService;

   @Autowired
   private CourseService courseService;

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

   @GetMapping("/manager")
   public Map<String, Object> getManagerList(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size){
      int offset = (page - 1) * size;
      List<Manager> managers = managerService.findAll(offset, size);
      int total = managerService.count();
      System.out.println(offset);
      Map<String, Object> response = new HashMap<>();
      response.put("items", managers);
      response.put("total", total);

      return response;
   }

   @GetMapping("/student")
   public Map<String, Object> getStudentList(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size){
      int offset = (page - 1) * size;
      List<Student> students = studentService.findAll(offset, size);
      int total = studentService.count();
      System.out.println(offset);
      Map<String, Object> response = new HashMap<>();
      response.put("items", students);
      response.put("total", total);

      return response;
   }

   @GetMapping("/teacher")
   public Map<String, Object> getTeacherList(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size){
      int offset = (page - 1) * size;
      List<Teacher> teachers = teacherService.findAll(offset, size);
      int total = teacherService.count();
      System.out.println(offset);
      Map<String, Object> response = new HashMap<>();
      response.put("items", teachers);
      response.put("total", total);

      return response;
   }



   @GetMapping("/course")
   public Map<String, Object> getCourseList(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size){
      int offset = (page - 1) * size;
      List<Course> courses = courseService.findAll(offset, size);
      int total = courseService.count();
      System.out.println(offset);
      Map<String, Object> response = new HashMap<>();
      response.put("items", courses);
      response.put("total", total);

      return response;
   }

   @GetMapping("/classroom")
   public Map<String, Object> getClassroomList(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size){
      int offset = (page - 1) * size;
      List<Classroom> classes = classroomService.findAll(offset, size);
      int total = classroomService.count();
      System.out.println(offset);
      Map<String, Object> response = new HashMap<>();
      response.put("items", classes);
      response.put("total", total);

      return response;
   }

   @GetMapping("/college")
   public Map<String, Object> getCollegeList(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size) {
      int offset = (page - 1) * size;
      List<College> colleges = collegeService.findAll(offset, size);
      int total = collegeService.count();

      Map<String, Object> response = new HashMap<>();
      response.put("items", colleges);
      response.put("total", total);

      return response;
   }

   @GetMapping("/major")
   public Map<String, Object> getMajorList(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size) {
      int offset = (page - 1) * size;
      List<Major> majors = majorService.findAll(offset, size);
      int total = majorService.count();

      Map<String, Object> response = new HashMap<>();
      response.put("items", majors);
      response.put("total", total);

      return response;
   }

   @GetMapping("/class")
   public Map<String, Object> getClassList(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size) {
      int offset = (page - 1) * size;
      List<School_Class> classes = school_classService.findAll(offset, size);
      int total = school_classService.count();

      Map<String, Object> response = new HashMap<>();
      response.put("items", classes);
      response.put("total", total);

      return response;
   }

   @GetMapping("/term")
   public Map<String, Object> getTermList(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size) {
      int offset = (page - 1) * size;
      List<Term> terms = termService.findAll(offset, size);
      int total = termService.count();

      Map<String, Object> response = new HashMap<>();
      response.put("items", terms);
      response.put("total", total);
      return response;
   }
}
