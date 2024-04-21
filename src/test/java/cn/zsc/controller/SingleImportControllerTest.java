package cn.zsc.controller;

import cn.zsc.academic_test.AcademicTestApplication;
import cn.zsc.entity.*;
import cn.zsc.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(classes = AcademicTestApplication.class)
@AutoConfigureMockMvc
public class SingleImportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private ManagerService managerService;

    @MockBean
    private ClassroomService classroomService;

    @MockBean
    private CourseService courseService;

    @MockBean
    private MajorService majorService;

    @MockBean
    private School_ClassService school_ClassService;

    @MockBean
    private TermService termService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addStudent_Success() throws Exception {
        Student student = new Student();
        // Set student properties
        student.setStu_num(1);
        student.setStu_username("newstudent");
        student.setStu_email("test@test.com");
        student.setStu_password("password");
        student.setStu_identity("NewStudent");
        student.setStu_class("class1");

        mockMvc.perform(post("/singleimport/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
               .andExpect(status().isCreated())
               .andExpect(content().string("Student added successfully."));
    }

    @Test
    void addStudent_Failure_InternalServerError() throws Exception {
        Student student = new Student();
        // Set student properties as required

        doThrow(new RuntimeException("Unexpected error")).when(studentService).insertStudent(any(Student.class));

        mockMvc.perform(post("/singleimport/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to add the student:")));
    }

    // Similar test methods for addTeacher, addManager, etc.
    @Test
    void addTeacher_Success() throws Exception {
        Teacher teacher = new Teacher();
        // Set teacher properties
        teacher.setTea_num(1);
        teacher.setTea_username("newteacher");
        teacher.setTea_email("test@test.com");
        teacher.setTea_password("password");
        teacher.setTea_identity("NewTeacher");

        mockMvc.perform(post("/singleimport/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacher)))
               .andExpect(status().isCreated())
               .andExpect(content().string("Teacher added successfully."));
    }

    @Test
    void addTeacher_Failure_InternalServerError() throws Exception {
        Teacher teacher = new Teacher();
        // Set teacher properties as required

        doThrow(new RuntimeException("Unexpected error")).when(teacherService).insertTeacher(any(Teacher.class));

        mockMvc.perform(post("/singleimport/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to add the teacher:")));

    }

    @Test
    void addManager_Success() throws Exception {
        Manager manager = new Manager();
        // Set manager properties
        manager.setMan_num(1);
        manager.setMan_username("newmanager");
        manager.setMan_email("test@test.com");
        manager.setMan_password("password");
        manager.setMan_identity("NewManager");

        mockMvc.perform(post("/singleimport/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(manager)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Manager added successfully."));
    }

    @Test
    void addManager_Failure_InternalServerError() throws Exception {
        Manager manager = new Manager();
        // Set manager properties as required

        doThrow(new RuntimeException("Unexpected error")).when(managerService).insertManager(any(Manager.class));

        mockMvc.perform(post("/singleimport/manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(manager)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to add the manager:")));
    }

    @Test
    void addClassroom_Success() throws Exception {
        Classroom classroom = new Classroom();
        classroom.setClassroom_id(1);
        classroom.setClassroom_name("classroom1");
        classroom.setClassroom_capacity(25);


        mockMvc.perform(post("/singleimport/classroom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(classroom)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Classroom added successfully."));
    }

    @Test
    void addClassroom_Failure_InternalServerError() throws Exception {
        Classroom classroom = new Classroom();
        // Set classroom properties as required

        doThrow(new RuntimeException("Unexpected error")).when(classroomService).insertClassroom(any(Classroom.class));

        mockMvc.perform(post("/singleimport/classroom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(classroom)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to add the classroom:")));
    }

    @Test
    void addCourse_Success() throws Exception {
        Course course = new Course();
        course.setCourse_id(1);
        course.setCourse_name("course1");
        course.setMajor_id(1);

        mockMvc.perform(post("/singleimport/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"course_id\":1,\"course_name\":\"course1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Course added successfully."));
    }

    @Test
    void addCourse_Failure_InternalServerError() throws Exception {
        Course course = new Course();
        // Set course properties as required

        doThrow(new RuntimeException("Unexpected error")).when(courseService).insertCourse(any(Course.class));

        mockMvc.perform(post("/singleimport/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to add the course:")));
    }

    @Test
    void addMajor_Success() throws Exception {
        Major major = new Major();
        major.setMajorId(1);
        major.setMajorName("major1");
        major.setMajor_collegeId(1);

        mockMvc.perform(post("/singleimport/major")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(major)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Major added successfully."));
    }

    @Test
    void addMajor_Failure_InternalServerError() throws Exception {
        Major major = new Major();
        // Set major properties as required

        doThrow(new RuntimeException("Unexpected error")).when(majorService).insertMajor(any(Major.class));

        mockMvc.perform(post("/singleimport/major")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(major)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to add the major:")));
    }

    @Test
    void addSchool_Class_Success() throws Exception {
        School_Class school_class = new School_Class();
        school_class.setClassName("class1");
        school_class.setClassYear(2020);
        school_class.setMajorId(1);

        mockMvc.perform(post("/singleimport/class")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(school_class)))
                .andExpect(status().isCreated())
                .andExpect(content().string("School_Class added successfully."));
    }

    @Test
    void addSchool_Class_Failure_InternalServerError() throws Exception {
        School_Class school_class = new School_Class();
        // Set school_class properties as required

        doThrow(new RuntimeException("Unexpected error")).when(school_ClassService).insertClass(any(School_Class.class));

        mockMvc.perform(post("/singleimport/class")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(school_class)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to add the school_class:")));
    }

    @Test
    void addTerm_Success() throws Exception {
        Term term = new Term();
        term.setTermId("1");
        term.setTermName("term1");
        term.setTermStartDate(Date.valueOf("2020-01-01"));
        term.setTermEndDate(Date.valueOf("2020-07-31"));


        mockMvc.perform(post("/singleimport/term")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(term)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Term added successfully."));
    }

    @Test
    void addTerm_Failure_InternalServerError() throws Exception {
        Term term = new Term();
        // Set term properties as required

        doThrow(new RuntimeException("Unexpected error")).when(termService).insertTerm(any(Term.class));

        mockMvc.perform(post("/singleimport/term")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(term)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Failed to add the term:")));
    }



}