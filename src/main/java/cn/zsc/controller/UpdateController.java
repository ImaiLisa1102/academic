package cn.zsc.controller;

import cn.zsc.entity.*;
import cn.zsc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/update")
public class UpdateController {

   @Autowired
   private BCryptPasswordEncoder passwordEncoder;

   @Autowired
   private ClassroomService classroomService;

   // 根据ID更新Classroom信息
   @PutMapping("/classroom")
   public ResponseEntity<String> updateClassroom(@RequestBody Classroom classroom) {
      return classroomService.updateClassroomInfo(classroom)
              ? ResponseEntity.ok("Classroom updated successfully")
              : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating classroom");
   }

   @Autowired
   private CollegeService collegeService;

   // 根据ID更新College信息
   @PutMapping("/college")
   public ResponseEntity<String> updateCollege(@RequestBody College college) {
      return collegeService.updateCollegeInfo(college)
              ? ResponseEntity.ok("College updated successfully")
              : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating college");
   }

   @Autowired
   private CourseService courseService;

   // 根据ID更新Course信息
   @PutMapping("/course")
   public ResponseEntity<String> updateCourse(@RequestBody Course course) {
      return courseService.updateCourseInfo(course)
              ? ResponseEntity.ok("Course updated successfully")
              : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating course");
   }

   @Autowired
   private MajorService majorService;

   // 根据ID更新Major信息
   @PutMapping("/major")
   public ResponseEntity<String> updateMajor(@RequestBody Major major) {
      return majorService.updateMajorInfo(major)
              ? ResponseEntity.ok("Major updated successfully")
              : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating major");
   }



   @Autowired
   private School_ClassService schoolClassService;

   // 根据班级名称更新School_Class信息
   @PutMapping("/class")
   public ResponseEntity<String> updateSchool_Class(@RequestBody School_Class school_class) {
      return schoolClassService.updateSchool_ClassInfo(school_class)
              ? ResponseEntity.ok("School_Class updated successfully")
              : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating school class");
   }

   @Autowired
   private ManagerService managerService;

   // 根据num更新Manager信息
   @PutMapping("/manager")
   public ResponseEntity<String> updateManager(@RequestBody Manager manager) {
      try {
         // 检查是否提供了新密码
         if (manager.getMan_password() != null && !manager.getMan_password().isEmpty()) {
            // 加密新密码
            String encryptedPassword = passwordEncoder.encode(manager.getMan_password());
            manager.setMan_password(encryptedPassword);
         }

         managerService.updateManagerInfo(manager);
         return ResponseEntity.ok("Manager updated successfully");
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating manager: " + e.getMessage());
      }
   }

   @Autowired
   private StudentService studentService;

   // 根据stu_num更新Student信息
   @PutMapping("/student")
   public ResponseEntity<String> updateStudent(@RequestBody Student student) {
      try {
         // 检查是否提供了新密码
         if (student.getStu_password() != null && !student.getStu_password().isEmpty()) {
            // 加密新密码
            String encryptedPassword = passwordEncoder.encode(student.getStu_password());
            student.setStu_password(encryptedPassword);
         }

         studentService.updateStudentInfo(student);
         return ResponseEntity.ok("Student updated successfully");
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating student: " + e.getMessage());
      }
   }

   // 根据stu_num更新学生密码
   @PutMapping("/student/password/{stuNum}")
   public ResponseEntity<?> updateStudentPassword(@PathVariable("stuNum") int stuNum,
                                                  @RequestBody Map<String, String> passwordInfo) {
      try {
         String currentPassword = studentService.findPasswordByStuNum(stuNum);
         String submittedCurrentPassword = passwordInfo.get("currentPassword");
         String newPassword = passwordInfo.get("newPassword");

         // 使用BCryptPasswordEncoder验证密码
         if (passwordEncoder.matches(submittedCurrentPassword, currentPassword)) {
            // 加密新密码并保存
            studentService.updatePasswordById(String.valueOf(stuNum), passwordEncoder.encode(newPassword));
            return ResponseEntity.ok("密码更新成功");
         } else {
            return ResponseEntity.badRequest().body("原密码不正确");
         }
      } catch (Exception e) {
         return ResponseEntity.internalServerError().body("密码更新过程中出错");
      }
   }



   @Autowired
   private TeacherService teacherService;

   // 根据tea_num更新Teacher信息
   @PutMapping("/teacher")
   public ResponseEntity<String> updateTeacher(@RequestBody Teacher teacher) {
      try {
         // 检查是否提供了新密码
         if (teacher.getTea_password() != null && !teacher.getTea_password().isEmpty()) {
            // 加密新密码
            String encryptedPassword = passwordEncoder.encode(teacher.getTea_password());
            teacher.setTea_password(encryptedPassword);
         }

         teacherService.updateTeacherInfo(teacher);
         return ResponseEntity.ok("Teacher updated successfully");
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating teacher: " + e.getMessage());
      }
   }

   //根据tea_num更新老师密码
   @PutMapping("/teacher/password/{teaNum}")
   public ResponseEntity<?> updateTeacherPassword(@PathVariable("teaNum") int teaNum,
                                                  @RequestBody Map<String, String> passwordInfo) {
      // 从请求体提取密码值
      String submittedCurrentPassword = passwordInfo.get("currentPassword");
      String newPassword = passwordInfo.get("newPassword");

      try {
         String currentPassword = teacherService.findTeacherPasswordByNum(teaNum);

         // 使用BCryptPasswordEncoder验证密码
         if(passwordEncoder.matches(submittedCurrentPassword, currentPassword)) {
            // 加密新密码并保存
            teacherService.updateTeacherPasswordByNum(teaNum, passwordEncoder.encode(newPassword));
            return ResponseEntity.ok("密码更新成功");
         } else {
            return ResponseEntity.badRequest().body("原密码不正确");
         }
      } catch (Exception e) {

         return ResponseEntity.internalServerError().body("密码更新过程中出错");
      }
   }

   @Autowired
   private TermService termService;

   // 根据termId更新Term信息
   @PutMapping("/term")
   public ResponseEntity<String> updateTerm(@RequestBody Term term) {
      return termService.updateTermInfo(term)
              ? ResponseEntity.ok("Term updated successfully")
              : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating term");
   }




}
