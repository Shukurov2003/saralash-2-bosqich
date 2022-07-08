package com.example.university.service;

import com.example.university.payload.DepartmentPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {

    ResponseEntity<?> createDepartment(DepartmentPayload departmentPayload);

    ResponseEntity<?>  createMultipleDepartment(List<DepartmentPayload> departmentsPayload);

    ResponseEntity<?> editDepartment(DepartmentPayload departmentPayload);
    void deleteDepartment(Long id);
    ResponseEntity<?> findAllDepartments();
    ResponseEntity<?> findDepartmentById(Long id);
}
