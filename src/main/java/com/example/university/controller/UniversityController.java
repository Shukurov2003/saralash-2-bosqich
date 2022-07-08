package com.example.university.controller;

import com.example.university.model.Result;
import com.example.university.payload.UniversityPayload;
import com.example.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/university")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @PostMapping("/multiple/save/")
    public ResponseEntity<?> createMultiple(@RequestBody List<UniversityPayload> universitiesPayload){
        if (universitiesPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the university is null ! "));
        }
        return universityService.createMultipleUniversity(universitiesPayload);
    }
    @PostMapping("/save/")
    public ResponseEntity<?> create(@RequestBody UniversityPayload universitiesPayload){
        if (universitiesPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the department is null ! "));
        }
        return universityService.createUniversity(universitiesPayload);
    }
    @PutMapping("/edit/")
    public ResponseEntity<?> edit(@RequestBody UniversityPayload universityPayload){
        if (universityPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the university is null ! "));
        }
        return universityService.editUniversity(universityPayload);
    }
    @DeleteMapping("/delete/{id}/")
    public void delete(@PathVariable Long id){
        if (id == null){
            System.out.println("Id is null");
        }
        universityService.deleteUniversity(id);
    }
    @GetMapping("/all/")
    public ResponseEntity<?> findAll(){
        return universityService.findAllUniversities();
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if (id==null){
            return ResponseEntity.badRequest().body(Result.error("Id is null ! "));
        }
        return universityService.findUniversityById(id);
    }

}
