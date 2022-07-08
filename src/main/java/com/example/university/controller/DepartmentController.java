package com.example.university.controller;

import com.example.university.model.Result;
import com.example.university.payload.DepartmentPayload;
import com.example.university.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/multiple/save/")
    public ResponseEntity<?>createMultiple(@RequestBody List<DepartmentPayload> departmentsPayload){
        if (departmentsPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the department is null ! "));
        }
        return departmentService.createMultipleDepartment(departmentsPayload);
    }
    @PostMapping("/save/")
    public ResponseEntity<?> create(@RequestBody DepartmentPayload departmentPayload){
        if (departmentPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the department is null ! "));
        }
        return departmentService.createDepartment(departmentPayload);
    }
    @PutMapping("/edit/")
    public ResponseEntity<?> edit(@RequestBody DepartmentPayload departmentPayload){
        if (departmentPayload==null){
            return ResponseEntity.badRequest().body(Result.error("The resource came from the department is null ! "));
        }
        return departmentService.editDepartment(departmentPayload);
    }
    @DeleteMapping("/delete/{id}/")
    public void delete(@PathVariable Long id){
        if (id == null){
            System.out.println("Id is null");
        }
        departmentService.deleteDepartment(id);
    }
    @GetMapping("/all/")
    public ResponseEntity<?> findAll(){
        return departmentService.findAllDepartments();
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if (id==null){
            return ResponseEntity.badRequest().body(Result.error("Id is null ! "));
        }
        return departmentService.findDepartmentById(id);
    }
}