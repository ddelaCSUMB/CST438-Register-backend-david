package com.cst438;



import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.cst438.controller.StudentController;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JunitTestPutOnHold {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void changeToOnHold() throws Exception {
    	
        //sample student
        Student sample = new Student();
        sample.setEmail("test@example.com");
        sample.setName("imsample");
        sample.setStatusCode(1);
        sample.setStatus("Active");
        
        //changing StatusCode and status for test
        sample.setStatusCode(0);
        sample.setStatus("On Hold");

        //mock
        Mockito.when(studentRepository.findByEmail(sample.getEmail())).thenReturn(null);
        Mockito.when(studentRepository.save(sample)).thenReturn(sample);

        //verify saved student
        Student savedStudent = studentController.addStudent(sample);
        assertEquals(sample, savedStudent);

        //verify step
        assertEquals(0, savedStudent.getStatusCode());
        assertEquals("On Hold", savedStudent.getStatus());
    }

    
    @Test
    public void checkForHold() throws Exception {
    	
        //sample student
        Student sample = new Student();
        sample.setEmail("test@example.com");
        sample.setName("imsample");
        sample.setStatusCode(0);
        sample.setStatus("On Hold");
        
        //check if status code is 0 (On Hold) change to 1 (Active)
        if(sample.getStatusCode() == 0) {
        	
        	sample.setStatusCode(1);
        
        	sample.setStatus("Active");
        }

        //mock
        Mockito.when(studentRepository.findByEmail(sample.getEmail())).thenReturn(null);
        Mockito.when(studentRepository.save(sample)).thenReturn(sample);

        //verify saved student
        Student savedStudent = studentController.addStudent(sample);
        assertEquals(sample, savedStudent);

        //verify step
        assertEquals(1, savedStudent.getStatusCode());
        assertEquals("Active", savedStudent.getStatus());
    }
    
    //alternatively the opposite of above test
    @Test
    public void checkForActive() throws Exception {
    	
        //sample student
        Student sample = new Student();
        sample.setEmail("test@example.com");
        sample.setName("imsample");
        sample.setStatusCode(1);
        sample.setStatus("Active");
        
        //check if status code is 0 (On Hold) change to 1 (Active)
        if(sample.getStatusCode() == 1) {
        	
        	sample.setStatusCode(0);
        
        	sample.setStatus("On Hold");
        }

        //mock
        Mockito.when(studentRepository.findByEmail(sample.getEmail())).thenReturn(null);
        Mockito.when(studentRepository.save(sample)).thenReturn(sample);

        //verify saved student
        Student savedStudent = studentController.addStudent(sample);
        assertEquals(sample, savedStudent);

        //verify step
        assertEquals(0, savedStudent.getStatusCode());
        assertEquals("On Hold", savedStudent.getStatus());
    }
}
