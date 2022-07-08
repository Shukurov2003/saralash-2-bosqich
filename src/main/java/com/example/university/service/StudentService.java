package com.example.university.service;

import com.example.university.payload.StudentPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<?> createMultipleStudents(List<StudentPayload> studentPayload);
    ResponseEntity<?> createStudent(StudentPayload studentPayload);
    ResponseEntity<?> editStudent(StudentPayload studentPayload);
    void deleteStudent(Long id);
    ResponseEntity<?> findAllStudents();
    ResponseEntity<?> findStudentById(Long id);
    ResponseEntity<?> findStudentByName(StudentPayload studentPayload);
}
