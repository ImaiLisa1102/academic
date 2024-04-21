package cn.zsc.controller;

import cn.zsc.academic_test.AcademicTestApplication;
import cn.zsc.entity.*;
import cn.zsc.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


import java.util.Collections;


@SpringBootTest(classes = AcademicTestApplication.class)
@AutoConfigureMockMvc
class ListControllerTest {

    @InjectMocks
    private ListController listController;

    @Mock
    private StudentService studentService;
    @Mock
    private ManagerService managerService;
    @Mock
    private CourseService courseService;
    @Mock
    private TeacherService teacherService;
    @Mock
    private ClassroomService classroomService;
    @Mock
    private CollegeService collegeService;
    @Mock
    private MajorService majorService;
    @Mock
    private School_ClassService school_classService;
    @Mock
    private TermService termService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Manager manager = new Manager();
        when(managerService.findAll(anyInt(), anyInt())).thenReturn(Collections.singletonList(manager));
        when(managerService.count()).thenReturn(1);

        // 如有需要添加对其他服务mock行为的设定
        //student
        Student student = new Student();
        when(studentService.findAll(anyInt(), anyInt())).thenReturn(Collections.singletonList(student));
        when(studentService.count()).thenReturn(1);
        //course
        Course course = new Course();
        when(courseService.findAll(anyInt(), anyInt())).thenReturn(Collections.singletonList(course));
        when(courseService.count()).thenReturn(1);
        //teacher
        Teacher teacher = new Teacher();
        when(teacherService.findAll(anyInt(), anyInt())).thenReturn(Collections.singletonList(teacher));
        when(teacherService.count()).thenReturn(1);
        //classroom
        Classroom classroom = new Classroom();
        when(classroomService.findAll(anyInt(), anyInt())).thenReturn(Collections.singletonList(classroom));
        when(classroomService.count()).thenReturn(1);
        //college
        College college = new College();
        when(collegeService.findAll(anyInt(), anyInt())).thenReturn(Collections.singletonList(college));
        when(collegeService.count()).thenReturn(1);
        //major
        Major major = new Major();
        when(majorService.findAll(anyInt(), anyInt())).thenReturn(Collections.singletonList(major));
    }

    @Test
    void testGetManagerList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/manager?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(8));
    }

    @Test
    void testGetClassroomList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/classroom?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(12));
    }

    @Test
    void testGetCollegeList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/college?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(5));
    }

    @Test
    void testGetSchoolClassList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/class?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(8));
    }

    @Test
    void testGetTermList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/term?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(4));
    }


    @Test
    void testGetStudentList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/student?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(142))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(15));
    }

    @Test
    void testGetTeacherList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/teacher?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(42))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(15));
    }

    @Test
    void testGetCourseList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/course?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(17))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(15));
    }

    @Test
    void testGetMajorList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list/major?page=1&size=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(23))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.length()").value(15));
    }



}

