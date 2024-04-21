package cn.zsc.controller;

import cn.zsc.service.ManagerService;
import cn.zsc.service.StudentService;
import cn.zsc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login/currentuser")
public class CurrentUserController {

   @Autowired
   private ManagerService managerService;

   @Autowired
   private StudentService studentService;

   @Autowired
   private TeacherService teacherService;

   @GetMapping("/manager/{manNum}")
   public ResponseEntity<String> getManagerIdentity(@PathVariable("manNum") int manNum) {
      String identity = managerService.getManagerIdentityByNum(manNum);
      return ResponseEntity.ok(identity);
   }

   @GetMapping("/student/{stuNum}")
   public ResponseEntity<String> getStudentIdentity(@PathVariable("stuNum") int stuNum) {
      String identity = studentService.getStudentIdentityByNum(stuNum);
      return ResponseEntity.ok(identity);
   }

   @GetMapping("/teacher/{teaNum}")
   public ResponseEntity<String> getTeacherIdentity(@PathVariable("teaNum") int teaNum) {
      String identity = teacherService.getTeacherIdentityByNum(teaNum);
      return ResponseEntity.ok(identity);
   }
}
