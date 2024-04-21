package cn.zsc.controller;

import cn.zsc.academic_test.AcademicTestApplication;
import cn.zsc.entity.*;
import cn.zsc.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = AcademicTestApplication.class)
@AutoConfigureMockMvc
class UpdateControllerTest {

   @Autowired
   private UpdateController updateController;

   @MockBean
   private ClassroomService classroomService;

   @MockBean
   private CollegeService collegeService;

   @MockBean
   private CourseService courseService;

   @MockBean
   private MajorService majorService;

   @MockBean
   private ManagerService managerService;

   @MockBean
   private School_ClassService schoolClassService;

   @MockBean
   private StudentService studentService;

   @MockBean
   private TeacherService teacherService;

   @MockBean
   private TermService termService;

   private Classroom classroom;
   private College college;
   private Course course;
   private Major major;
   private Manager manager;
   private School_Class schoolClass;
   private Student student;
   private Teacher teacher;
   private Term term;

   @BeforeEach
   void setUp() {
      classroom = new Classroom();
      college = new College();
      course = new Course();
      major = new Major();
      manager = new Manager();
      schoolClass = new School_Class();
      student = new Student();
      teacher = new Teacher();
      term = new Term();
   }

   @Test
   void testUpdateClassroom() {
      when(classroomService.updateClassroomInfo(classroom)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateClassroom(classroom);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("Classroom updated successfully", response.getBody());
   }

   @Test
   void testUpdateCollege() {
      when(collegeService.updateCollegeInfo(college)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateCollege(college);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("College updated successfully", response.getBody());
   }

   @Test
   void testUpdateCourse() {
      when(courseService.updateCourseInfo(course)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateCourse(course);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("Course updated successfully", response.getBody());
   }

   @Test
   void testUpdateMajor() {
      when(majorService.updateMajorInfo(major)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateMajor(major);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("Major updated successfully", response.getBody());
   }

   @Test
   void testUpdateManager() {
      when(managerService.updateManagerInfo(manager)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateManager(manager);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("Manager updated successfully", response.getBody());
   }

   @Test
   void testUpdateSchoolClass() {
      when(schoolClassService.updateSchool_ClassInfo(schoolClass)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateSchool_Class(schoolClass);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("School_Class updated successfully", response.getBody());
   }

   @Test
   void testUpdateStudent() {
      when(studentService.updateStudentInfo(student)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateStudent(student);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("Student updated successfully", response.getBody());
   }

   @Test
   void testUpdateStudentPassword() {
      int stuNum = 123;
      String currentPassword = "currentPass";
      String newPassword = "newPass";
      Map<String, String> passwordInfo = new HashMap<>();
      passwordInfo.put("currentPassword", currentPassword);
      passwordInfo.put("newPassword", newPassword);

      when(studentService.findPasswordByStuNum(stuNum)).thenReturn(currentPassword);
      ResponseEntity<?> response = updateController.updateStudentPassword(stuNum, passwordInfo);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("密码更新成功", response.getBody());

      passwordInfo.put("currentPassword", "incorrectPass");
      response = updateController.updateStudentPassword(stuNum, passwordInfo);
      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals("原密码不正确", response.getBody());
   }

   @Test
   void testUpdateTeacher() {
      when(teacherService.updateTeacherInfo(teacher)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateTeacher(teacher);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("Teacher updated successfully", response.getBody());
   }

   @Test
   void testUpdateTeacherPassword() {
      int teaNum = 456;
      String currentPassword = "currentPass";
      String newPassword = "newPass";
      Map<String, String> passwordInfo = new HashMap<>();
      passwordInfo.put("currentPassword", currentPassword);
      passwordInfo.put("newPassword", newPassword);

      when(teacherService.findTeacherPasswordByNum(teaNum)).thenReturn(currentPassword);
      ResponseEntity<?> response = updateController.updateTeacherPassword(teaNum, passwordInfo);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("密码更新成功", response.getBody());

      passwordInfo.put("currentPassword", "incorrectPass");
      response = updateController.updateTeacherPassword(teaNum, passwordInfo);
      assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
      assertEquals("原密码不正确", response.getBody());
   }

   @Test
   void testUpdateTerm() {
      when(termService.updateTermInfo(term)).thenReturn(true);
      ResponseEntity<String> response = updateController.updateTerm(term);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals("Term updated successfully", response.getBody());
   }
}
