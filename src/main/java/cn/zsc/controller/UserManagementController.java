package cn.zsc.controller;

import cn.zsc.entity.Student;
import cn.zsc.entity.Teacher;
import cn.zsc.service.StudentService;
import cn.zsc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserManagementController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    // 学生信息更新
//    @PutMapping("/update/student")
//    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
//        return studentService.updateStudentInfo(student)
//                ? ResponseEntity.ok("学生信息更新成功")
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新学生信息失败");
//    }
//
//    // 更新学生密码
//    @PutMapping("/update/student/password/{stuNum}")
//    public ResponseEntity<?> updateStudentPassword(@PathVariable("stuNum") int stuNum,
//                                                   @RequestBody Map<String, String> passwordInfo) {
//        try {
//            String currentPassword = studentService.findPasswordByStuNum(stuNum);
//            String submittedCurrentPassword = passwordInfo.get("currentPassword");
//            String newPassword = passwordInfo.get("newPassword");
//
//            if (submittedCurrentPassword.equals(currentPassword)) {
//                studentService.updatePasswordById(String.valueOf(stuNum), newPassword);
//                return ResponseEntity.ok("密码更新成功");
//            } else {
//                return ResponseEntity.badRequest().body("原密码不正确");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("密码更新过程中出错");
//        }
//    }
//
//    // 获取学生列表
//    @GetMapping("/list/student")
//    public Map<String, Object> getStudentList(@RequestParam(defaultValue = "1") Integer page,
//                                              @RequestParam(defaultValue = "10") Integer size) {
//        int offset = (page - 1) * size;
//        List<Student> students = studentService.findAll(offset, size);
//        int total = studentService.count();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("items", students);
//        response.put("total", total);
//        return response;
//    }
//
//    // 删除学生
//    @DeleteMapping("/delete/student/{stuNum}")
//    public void deleteStudent(@PathVariable Long stuNum) {
//        studentService.deleteStudent(stuNum);
//    }
//
//
//    @GetMapping("/find/student/{stu_num}")
//    public ResponseEntity<Student> getStudentByStudentNumber(@PathVariable int stu_num) {
//        try {
//            Student student = studentService.findStudentByStudentNumber(stu_num);
//
//            if (student != null) {
//                return ResponseEntity.ok(student);
//            } else {
//                // Student not found
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//        } catch (Exception e) {
//            // Handle exceptions and return an appropriate response
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PostMapping("/singleimport/student")
//    public ResponseEntity<String> addStudent(@RequestBody Student student) {
//        try {
//            studentService.insertStudent(student);
//            return new ResponseEntity<>("Student added successfully.", HttpStatus.CREATED);
//        } catch (Exception e) {
//            // Log the exception here using a logger
//            return new ResponseEntity<>("Failed to add the student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    @GetMapping("/userinfo/student/{stuNum}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable int stuNum) {
        Student student = studentService.getStudentByStuNum(stuNum);
        if(student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/userinfo/teacher/{teaNum}")
    public ResponseEntity<Teacher> getTeacherInfo(@PathVariable int teaNum) {
        Teacher teacher = teacherService.getTeacherByTeaNum(teaNum);
        if(teacher != null) {
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    // 教师信息更新
//    @PutMapping("/update/teacher")
//    public ResponseEntity<String> updateTeacher(@RequestBody Teacher teacher) {
//        return teacherService.updateTeacherInfo(teacher)
//                ? ResponseEntity.ok("教师信息更新成功")
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新教师信息失败");
//    }
//
//    // 更新教师密码
//    @PutMapping("/update/teacher/password/{teaNum}")
//    public ResponseEntity<?> updateTeacherPassword(@PathVariable("teaNum") int teaNum,
//                                                   @RequestBody Map<String, String> passwordInfo) {
//        try {
//            String currentPassword = teacherService.findTeacherPasswordByNum(teaNum);
//            String submittedCurrentPassword = passwordInfo.get("currentPassword");
//            String newPassword = passwordInfo.get("newPassword");
//
//            if (submittedCurrentPassword.equals(currentPassword)) {
//                teacherService.updateTeacherPasswordByNum(teaNum, newPassword);
//                return ResponseEntity.ok("密码更新成功");
//            } else {
//                return ResponseEntity.badRequest().body("原密码不正确");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("密码更新过程中出错");
//        }
//    }
//
//    // 获取教师列表
//    @GetMapping("/list/teacher")
//    public Map<String, Object> getTeacherList(@RequestParam(defaultValue = "1") Integer page,
//                                              @RequestParam(defaultValue = "10") Integer size) {
//        int offset = (page - 1) * size;
//        List<Teacher> teachers = teacherService.findAll(offset, size);
//        int total = teacherService.count();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("items", teachers);
//        response.put("total", total);
//        return response;
//    }
//
//    // 删除教师
//    @DeleteMapping("/delete/teacher/{teaNum}")
//    public void deleteTeacher(@PathVariable Long teaNum) {
//        teacherService.deleteTeacher(teaNum);
//    }
//
//    @GetMapping("/find/teacher/{tea_num}")
//    public ResponseEntity<Teacher> getTeacherByTeacherNumber(@PathVariable int tea_num) {
//        try {
//            Teacher teacher = teacherService.findTeacherByTeacherNumber(tea_num);
//
//            if (teacher != null) {
//                return ResponseEntity.ok(teacher);
//            } else {
//                // Teacher not found
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//        } catch (Exception e) {
//            // Handle exceptions and return an appropriate response
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PostMapping("singleimport/teacher")
//    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher) {
//        try {
//            teacherService.insertTeacher(teacher);
//            return new ResponseEntity<>("Teacher added successfully.", HttpStatus.CREATED);
//        } catch (Exception e) {
//            // Log the exception here using a logger
//            return new ResponseEntity<>("Failed to add the teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



}
