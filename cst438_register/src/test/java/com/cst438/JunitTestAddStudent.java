package com.cst438;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.controller.StudentController;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class JunitTestAddStudent {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testAddStudent() throws Exception {
    	
        //sample student
        Student sample = new Student();
        sample.setEmail("test@example.com");
        sample.setName("imsample");
        sample.setStatusCode(0);
        sample.setStatus("On Hold");

        //mock
        Mockito.when(studentRepository.findByEmail(sample.getEmail())).thenReturn(null);
        Mockito.when(studentRepository.save(sample)).thenReturn(sample);

        Student savedStudent = studentController.addStudent(sample);

        assertEquals(sample, savedStudent); //verify step
    }

}
