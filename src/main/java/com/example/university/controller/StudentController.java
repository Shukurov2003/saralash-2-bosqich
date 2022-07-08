package com.example.university.controller;

import com.example.university.model.Result;
import com.example.university.payload.StudentPayload;
import com.example.university.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/multiple/save/")
    public ResponseEntity<?> createMultiple(@RequestBody List<StudentPayload> studentsPayload){
        if (studentsPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the student is null ! "));
        }
        return studentService.createMultipleStudents(studentsPayload);
    }
    @PostMapping("/save/")
    public ResponseEntity<?> create(@RequestBody StudentPayload studentPayload){
        if (studentPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the student is null ! "));
        }
        return studentService.createStudent(studentPayload);
    }
    @PutMapping("/edit/")
    public ResponseEntity<?> edit(@RequestBody StudentPayload studentPayload){
        if (studentPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the student is null ! "));
        }
        return studentService.editStudent(studentPayload);
    }
    @DeleteMapping("/delete/{id}/")
    public void delete(@PathVariable Long id) {
        if (id == null) {
            System.out.println("Id is null");
        }
        studentService.deleteStudent(id);
    }
    @GetMapping("/all/")
    public ResponseEntity<?> findAll(){
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if (id==null){
            return ResponseEntity.badRequest().body(Result.error("Id is null ! "));
        }
        return studentService.findStudentById(id);
    }
    @GetMapping("/")
    public ResponseEntity<?> findByName(@RequestBody StudentPayload studentPayload){
        if (studentPayload.getFullName()==null){
            return ResponseEntity.badRequest().body(Result.error("name is null ! "));
        }
        return studentService.findStudentByName(studentPayload);
    }
}
