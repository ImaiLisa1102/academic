package cn.zsc.controller;

import cn.zsc.entity.User;
import cn.zsc.service.ManagerService;
import cn.zsc.service.StudentService;
import cn.zsc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ManagerService managerService;

    @PostMapping("/login/student")
    public ResponseEntity<String> loginStudent(@RequestBody User user) {
        String passwordHash = studentService.findPasswordByStuNum(user.getUserId());
        return performLogin(user.getPassword(), passwordHash);
    }

    @PostMapping("/login/teacher")
    public ResponseEntity<String> loginTeacher(@RequestBody User user) {
        String passwordHash = teacherService.findTeacherPasswordByNum(user.getUserId());
        return performLogin(user.getPassword(), passwordHash);
    }

    @PostMapping("/login/manager")
    public ResponseEntity<String> loginManager(@RequestBody User user) {
        String passwordHash = managerService.findPasswordByNum(user.getUserId());
        return performLogin(user.getPassword(), passwordHash);
    }

    private ResponseEntity<String> performLogin(String plainPassword, String passwordHash) {
        try {
            if (!passwordEncoder.matches(plainPassword, passwordHash)) {
                throw new BadCredentialsException("Invalid password");
            }

            // Upon successful authentication, return successful login response
            // You may also want to return a JWT token or any other auth token
            return ResponseEntity.ok("User logged in successfully");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Login failed: Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: An unexpected error occurred");
        }
    }
}