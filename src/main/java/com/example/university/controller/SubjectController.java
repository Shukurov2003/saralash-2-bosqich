package com.example.university.controller;

import com.example.university.model.Result;
import com.example.university.payload.SubjectPayload;
import com.example.university.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor

public class  SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/multiple/save/")
    public ResponseEntity<?> createMultiple(@RequestBody List<SubjectPayload> subjectsPayload) {
        if (subjectsPayload == null) {
            return ResponseEntity.badRequest().body(Result.error("The resource came from the subjects is null ! "));
        }
        return subjectService.createMultipleSubjects(subjectsPayload);
    }

    @PostMapping("/save/")
    public ResponseEntity<?> create(@RequestBody SubjectPayload subjectPayload) {
        if (subjectPayload == null) {
            return ResponseEntity.badRequest().body(Result.error("The resource came from the subject is null ! "));
        }
        return subjectService.createSubject(subjectPayload);
    }

    @PutMapping("/edit/")
    public ResponseEntity<?> edit(@RequestBody SubjectPayload subjectPayload) {
        if (subjectPayload == null) {
            return ResponseEntity.badRequest().body(Result.error("The resource came from the subject is null ! "));
        }
        if (subjectPayload.getId() == null) {
            return ResponseEntity.badRequest().body(Result.error("The id came from the department is null ! "));
        }
        return subjectService.editSubject(subjectPayload);
    }

    @DeleteMapping("/delete/{id}/")
    public void delete(@PathVariable Long id) {
        if (id == null) {
            System.out.println("Id is null");
        }
        subjectService.deleteSubject(id);
    }

    @GetMapping("/all/")
    public ResponseEntity<?> findAll() {
        return subjectService.findAllSubjects();
    }
}
