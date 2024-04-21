package cn.zsc.controller;

import cn.zsc.academic_test.AcademicTestApplication;
import cn.zsc.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(classes = AcademicTestApplication.class)
@AutoConfigureMockMvc
public class DeleteControllerTest {

   @Mock
   private ClassroomService classroomService;

   @Mock
   private CourseService courseService;

   @Mock
   private ManagerService managerService;

   @Mock
   private StudentService studentService;

   @Mock
   private TeacherService teacherService;

   @Mock
   private CollegeService collegeService;

   @Mock
   private School_ClassService school_classService;

   @Mock
   private TermService termService;

   @Mock
   private MajorService majorService;

   @InjectMocks
   private DeleteController deleteController;

   private MockMvc mockMvc;

   @Test
   public void testDeleteClassroom() throws Exception {
      mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
      mockMvc.perform(MockMvcRequestBuilders.delete("/delete/classroom/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void testDeleteCourse() throws Exception {
      // test logic for deleteCourse
       mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
       mockMvc.perform(MockMvcRequestBuilders.delete("/delete/course/1"))
             .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void testDeleteManager() throws Exception {
      // test logic for deleteManager
      mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
      mockMvc.perform(MockMvcRequestBuilders.delete("/delete/manager/1"))
             .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void testDeleteStudent() throws Exception {
      // test logic for deleteStudent
      mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
      mockMvc.perform(MockMvcRequestBuilders.delete("/delete/student/1"))
             .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void testDeleteTeacher() throws Exception {
      // test logic for deleteTeacher
      mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
      mockMvc.perform(MockMvcRequestBuilders.delete("/delete/teacher/1"))
             .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void testDeleteCollege() throws Exception {
      // test logic for deleteCollege
      mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
      mockMvc.perform(MockMvcRequestBuilders.delete("/delete/college/1"))
             .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void testDeleteClass() throws Exception {
      // test logic for deleteClass
      mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
      mockMvc.perform(MockMvcRequestBuilders.delete("/delete/class/1"))
             .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void testDeleteTerm() throws Exception {
      // test logic for deleteTerm
      mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
      mockMvc.perform(MockMvcRequestBuilders.delete("/delete/term/1"))
             .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void testDeleteMajor() throws Exception {
      // test logic for deleteMajor
      mockMvc = MockMvcBuilders.standaloneSetup(deleteController).build();
      mockMvc.perform(MockMvcRequestBuilders.delete("/delete/major/1"))
             .andExpect(MockMvcResultMatchers.status().isOk());
   }
}
