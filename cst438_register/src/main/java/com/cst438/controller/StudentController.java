package com.cst438.controller;

import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) throws Exception {
        //check for email
        Student emailExists = studentRepository.findByEmail(student.getEmail());
        if (emailExists != null) {
            throw new Exception("Email already exists");
        }

        //save to db
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }
    
    @PutMapping("/{id}/on-hold")
    public Student putStudentOnHold(@PathVariable("id") int studentId) throws Exception {
        //check by studentId
        Optional<Student> optStudent = studentRepository.findById(studentId);
        if (!optStudent.isPresent()) {
            throw new Exception("Student not found");
        }

        //set On hold and status code 0
        Student student = optStudent.get();
        student.setStatus("On Hold");
        student.setStatusCode(0);
        Student updatedStudent = studentRepository.save(student);
        return updatedStudent;
    }
    
    @PutMapping("/{id}/off-hold")
    public Student changeStudentStatus(@PathVariable("id") int studentId) throws Exception {
        //check by studentId
        Optional<Student> optStudent = studentRepository.findById(studentId);
        if (!optStudent.isPresent()) {
            throw new Exception("Student not found");
        }
        
        Student student = optStudent.get();
        
        //change to active and status code 1
        student.setStatus("Active");
        student.setStatusCode(1);
        
        Student updatedStudent = studentRepository.save(student);
        return updatedStudent;
    }
    
}