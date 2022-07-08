package com.example.university.service.Impl;

import com.example.university.Repository.StudentRepository;
import com.example.university.entity.Department;
import com.example.university.entity.Faculty;
import com.example.university.entity.Group;
import com.example.university.entity.Student;
import com.example.university.exeptions.BadRequestException;
import com.example.university.exeptions.ResourceNotFoundException;
import com.example.university.model.Result;
import com.example.university.payload.StudentPayload;
import com.example.university.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<?> createMultipleStudents(List<StudentPayload> studentPayload) {

       try {
           List<Student> students = new ArrayList<>();

           for (StudentPayload payload : studentPayload) {
               Student student = new Student(
                       payload.getFullName(),
                       payload.getBirthOfDate(),
                       payload.getAddress(),
                       payload.getContractType()
               );
               students.add(student);
           }
           students = studentRepository.saveAll(students);
           return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), students));
       }catch (BadRequestException e){
           log.error("you are failed to save the students");
           return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
       }
    }

    @Override
    public ResponseEntity<?> createStudent(StudentPayload studentPayload) {
        try{
            Student student = new Student(
                    studentPayload.getFullName(),
                    studentPayload.getBirthOfDate(),
                    studentPayload.getAddress(),
                    studentPayload.getContractType()
            );
            student = studentRepository.save(student);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), student));
        }catch (BadRequestException e){
            log.error("you are failed to save the student");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> editStudent(StudentPayload studentPayload) {
        try{
            Student student = new Student(
                    studentPayload.getId(),
                    studentPayload.getFullName(),
                    studentPayload.getBirthOfDate(),
                    studentPayload.getAddress(),
                    studentPayload.getContractType()
            );
            student = studentRepository.save(student);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), student));
        }catch (BadRequestException e){
            log.error("you are failed to save the student");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> findAllStudents() {
        try {
            List<Student> students = studentRepository.findAll();
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),students));
        }catch (ResourceNotFoundException e){
            log.error("Not resources found");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> findStudentById(Long id) {

        try {
            Optional<Student> student = studentRepository.findById(id);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),student));
        }catch (ResourceNotFoundException e){
            log.error("Not resource found");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> findStudentByName(StudentPayload studentPayload) {
        try {
            Optional<Student> student = Optional.ofNullable(studentRepository.findStudentByFullName(studentPayload.getFullName()));
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),student));
        }catch (ResourceNotFoundException e){
            log.error("Not resource found");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }
}
