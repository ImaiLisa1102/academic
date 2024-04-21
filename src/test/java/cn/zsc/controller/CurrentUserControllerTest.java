package cn.zsc.controller;

import cn.zsc.academic_test.AcademicTestApplication;
import cn.zsc.service.ManagerService;
import cn.zsc.service.StudentService;
import cn.zsc.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = AcademicTestApplication.class)
@AutoConfigureMockMvc
public class CurrentUserControllerTest {

    @Mock
    private ManagerService managerService;

    @Mock
    private StudentService studentService;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private CurrentUserController currentUserController;

    @Test
    public void testGetManagerIdentity_HappyPath() {
        when(managerService.getManagerIdentityByNum(123)).thenReturn("Manager");
        ResponseEntity<String> response = currentUserController.getManagerIdentity(123);
        assertEquals("Manager", response.getBody());
    }

    @Test
    public void testGetStudentIdentity_HappyPath() {
        when(studentService.getStudentIdentityByNum(456)).thenReturn("Student");
        ResponseEntity<String> response = currentUserController.getStudentIdentity(456);
        assertEquals("Student", response.getBody());
    }

    @Test
    public void testGetTeacherIdentity_HappyPath() {
        when(teacherService.getTeacherIdentityByNum(789)).thenReturn("Teacher");
        ResponseEntity<String> response = currentUserController.getTeacherIdentity(789);
        assertEquals("Teacher", response.getBody());
    }

    @Test
    public void testGetManagerIdentity_InvalidInput() {
        when(managerService.getManagerIdentityByNum(anyInt())).thenReturn("Invalid Manager");
        ResponseEntity<String> response = currentUserController.getManagerIdentity(-1);
        assertEquals("Invalid Manager", response.getBody());
    }

    @Test
    public void testGetStudentIdentity_InvalidInput() {
        when(studentService.getStudentIdentityByNum(anyInt())).thenReturn("Invalid Student");
        ResponseEntity<String> response = currentUserController.getStudentIdentity(0);
        assertEquals("Invalid Student", response.getBody());
    }

    @Test
    public void testGetTeacherIdentity_InvalidInput() {
        when(teacherService.getTeacherIdentityByNum(anyInt())).thenReturn("Invalid Teacher");
        ResponseEntity<String> response = currentUserController.getTeacherIdentity(-100);
        assertEquals("Invalid Teacher", response.getBody());
    }
}
