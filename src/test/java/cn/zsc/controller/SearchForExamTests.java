package cn.zsc.controller;

import cn.zsc.academic_test.AcademicTestApplication;
import cn.zsc.entity.Course;
import cn.zsc.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AcademicTestApplication.class)
@AutoConfigureMockMvc
class SearchForExamTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TermService termService;

    @MockBean
    private MajorService majorService;

    @MockBean
    private School_ClassService schoolClassService;

    @MockBean
    private CourseClassService courseClassService;

    @MockBean
    private CourseService courseService;

    @Test
    void getAllTermId_ShouldReturnAllTerms() throws Exception {
        List<String> termIds = Arrays.asList("2022A", "2022B", "2023A");
        when(termService.getAllTermIds()).thenReturn(termIds);

        mockMvc.perform(get("/exam/search/terms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$", is(termIds)));
        verify(termService, times(1)).getAllTermIds();
    }

    @Test
    void getAllMajorNames_ShouldReturnAllMajorNames() throws Exception {
        List<String> majorNames = Arrays.asList("Computer Science", "Mechanical Engineering", "English Literature");
        when(majorService.getAllMajorNames()).thenReturn(majorNames);

        mockMvc.perform(get("/exam/search/majors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$", is(majorNames)));
        verify(majorService, times(1)).getAllMajorNames();
    }

    @Test
    void findMajorIdByName_ValidName_ShouldReturnMajorId() throws Exception {
        when(majorService.findMajorIdByName("Computer Science")).thenReturn(123);

        mockMvc.perform(get("/exam/search/singlemajor/Computer Science"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(123)));
        verify(majorService, times(1)).findMajorIdByName("Computer Science");
    }

    @Test
    void findMajorIdByName_InvalidName_ShouldReturnNotFound() throws Exception {
        when(majorService.findMajorIdByName("Unknown Major")).thenReturn(null);

        mockMvc.perform(get("/exam/search/singlemajor/Unknown Major"))
                .andExpect(status().isNotFound());
        verify(majorService, times(1)).findMajorIdByName("Unknown Major");
    }

    @Test
    void getClassNamesByMajorId_ValidId_ShouldReturnClassNames() throws Exception {
        List<String> classNames = Arrays.asList("ClassA", "ClassB", "ClassC");
        int majorId = 123;
        when(schoolClassService.findClassNamesByMajorId(majorId)).thenReturn(classNames);

        mockMvc.perform(get("/exam/search/classnames/{majorId}", majorId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("ClassA")))
                .andExpect(jsonPath("$[1]", is("ClassB")))
                .andExpect(jsonPath("$[2]", is("ClassC")));
        verify(schoolClassService, times(1)).findClassNamesByMajorId(majorId);
    }

    @Test
    void findCourseIdsByTermAndClassName_ValidInputs_ShouldReturnCourseIds() throws Exception {
        List<Integer> courseIds = Arrays.asList(1, 2, 3);
        String termId = "2022A";
        String className = "ClassA";
        when(courseClassService.findCourseIdsByTermAndClassName(termId, className)).thenReturn(courseIds);

        mockMvc.perform(get("/exam/search/findCourseIds/{termId}/{className}", termId, className))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is(1)))
                .andExpect(jsonPath("$[1]", is(2)))
                .andExpect(jsonPath("$[2]", is(3)));
        verify(courseClassService, times(1)).findCourseIdsByTermAndClassName(termId, className);
    }

    @Test
    void getCourseById_ValidId_ShouldReturnCourse() throws Exception {
        int courseId = 123;
        Course course = new Course(); // Assuming there's a constructor or setters to initialize
        when(courseService.findCourseById(courseId)).thenReturn(course);

        mockMvc.perform(get("/exam/search/course/{courseId}", courseId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // Additional assertions can be added to verify the course object attributes if needed
        verify(courseService, times(1)).findCourseById(courseId);
    }

    @Test
    void getCourseById_InvalidId_ShouldReturnNotFoundStatus() throws Exception {
        int courseId = 999;
        when(courseService.findCourseById(courseId)).thenReturn(null);

        mockMvc.perform(get("/exam/search/course/{courseId}", courseId))
                .andExpect(status().isNotFound());
        verify(courseService, times(1)).findCourseById(courseId);
    }

}