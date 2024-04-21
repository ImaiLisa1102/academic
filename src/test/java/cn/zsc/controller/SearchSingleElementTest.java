package cn.zsc.controller;

import cn.zsc.academic_test.AcademicTestApplication;
import cn.zsc.entity.Manager;
import cn.zsc.service.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = AcademicTestApplication.class)
@AutoConfigureMockMvc
class SearchSingleElementTest {

    @InjectMocks
    private SearchSingleElement searchSingleElement;

    @Mock
    private ManagerService managerService;

    @BeforeEach
    void setUp() {
        // Initialize the controller and mocks before each test
    }

    @Test
    void getManagerByManagerNumber_Found() {
        // Arrange
        int man_num = 1;
        Manager mockManager = new Manager();
        // ... Initialize mock manager fields

        doReturn(mockManager).when(managerService).findManagerByManagerNumber(man_num);

        // Act
        ResponseEntity<Manager> response = searchSingleElement.getManagerByManagerNumber(man_num);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockManager, response.getBody());
    }

    @Test
    void getManagerByManagerNumber_NotFound() {
        // Arrange
        int man_num = 2;
        doReturn(null).when(managerService).findManagerByManagerNumber(man_num);

        // Act
        ResponseEntity<Manager> response = searchSingleElement.getManagerByManagerNumber(man_num);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getManagerByManagerNumber_InternalServerError() {
        // Arrange
        int man_num = 3;
        doThrow(new RuntimeException("Internal error")).when(managerService).findManagerByManagerNumber(anyInt());

        // Act
        ResponseEntity<Manager> response = searchSingleElement.getManagerByManagerNumber(man_num);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // Write additional test cases for other methods in the controller like getTeacherByTeacherNumber,
    // getStudentByStudentNumber, ...

}