package cn.zsc.controller;

import cn.zsc.entity.User;
import cn.zsc.academic_test.AcademicTestApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import cn.zsc.service.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = AcademicTestApplication.class)
@AutoConfigureMockMvc
public class ExamArrangementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    @MockBean
    private ClassroomService classroomService;
    @MockBean
    private TeacherService teacherService;
    // ... 确保为需要的每个服务创建 MockBean
    @MockBean
    private School_ClassService school_classService;
    @MockBean
    private CourseClassroomArrangeService courseClassroomArrangeService;
    @MockBean
    private ExamArrangementService examArrangementService;
    @MockBean
    private ExamTeacherArrangeService examTeacherArrangeService;

    @Test
    public void arrangeExams_WhenCalled_ReturnsSuccess() throws Exception {
        // 构建测试数据，模拟服务层调用返回
        when(school_classService.getClassYearByClassName(anyString())).thenReturn(2021);
        when(studentService.getStudentCountInClass(anyString())).thenReturn(100L);
        // 根据实际的业务逻辑调整返回值，确保它们符合你的测试用例预期
        // when(examArrangementService.insertExamArrangement(any(ExamArrangement.class))).thenReturn(...);

        // 模拟前端发送的请求数据
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("courseInfoArray", new ArrayList<>());
        requestData.put("dateArray", Arrays.asList("2021-05-01", "2021-05-02"));

        this.mockMvc.perform(post("/exam/arrangement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestData)))
                .andExpect(status().isOk())
                // 根据你控制器逻辑的实际返回内容调整这里的期望值
                .andExpect(content().string("考试安排成功"));
    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}