package com.example.university.controller;


import com.example.university.model.Result;
import com.example.university.payload.FacultyPayload;
import com.example.university.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @PostMapping("/multiple/save/")
    public ResponseEntity<?> createMultiple(@RequestBody List<FacultyPayload> facultiesPayload) {
        if (facultiesPayload == null) {
            return ResponseEntity.badRequest().body(Result.error("The resource came from the faculties is null ! "));
        }
        return facultyService.createMultipleFaculty(facultiesPayload);
    }

    @PostMapping("/save/")
    public ResponseEntity<?> create(@RequestBody FacultyPayload facultyPayload) {
        if (facultyPayload == null) {
            return ResponseEntity.badRequest().body(Result.error("The resource came from the faculty is null ! "));
        }
        return facultyService.createFaculty(facultyPayload);
    }

    @PutMapping("/edit/")
    public ResponseEntity<?> edit(@RequestBody FacultyPayload facultyPayload) {
        if (facultyPayload == null) {
            return ResponseEntity.badRequest().body(Result.error("The resource came from the department is null ! "));
        }
        if (facultyPayload.getId() == null) {
            return ResponseEntity.badRequest().body(Result.error("The id came from the department is null ! "));
        }
        return facultyService.editFaculty(facultyPayload);
    }

    @DeleteMapping("/delete/{id}/")
    public void delete(@PathVariable Long id) {
        if (id == null) {
            System.out.println("Id is null");
        }
        facultyService.deleteFaculty(id);
    }

    @GetMapping("/all/")
    public ResponseEntity<?> findAll() {
        return facultyService.findAllFaculties();
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(Result.error("Id is null ! "));
        }
        return facultyService.findFacultyById(id);
    }
}
