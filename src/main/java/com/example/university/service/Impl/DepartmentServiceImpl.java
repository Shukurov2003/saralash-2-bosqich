package com.example.university.service.Impl;

import com.example.university.Repository.DepartmentRepository;
import com.example.university.entity.Department;
import com.example.university.entity.Group;
import com.example.university.exeptions.BadRequestException;
import com.example.university.exeptions.ResourceNotFoundException;
import com.example.university.model.Result;
import com.example.university.payload.DepartmentPayload;
import com.example.university.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;


    @Override
    public ResponseEntity<?> createDepartment(DepartmentPayload departmentPayload) {

        try {
            Department department = new Department();
            department.setName(departmentPayload.getName());
            List<Group> groups = new ArrayList<>();
            if (departmentPayload.getGroupIds() != null) {
                for (Long groupId : departmentPayload.getGroupIds()) {
                    Group group = new Group();
                    group.setId(groupId);
                    groups.add(group);
                }
                department.setGroups(groups);
            }
            department = departmentRepository.save(department);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), department));
        } catch (BadRequestException e) {
            log.error("you are failed to save the department");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }

    }

    @Override
    public ResponseEntity<?> createMultipleDepartment(List<DepartmentPayload> departmentsPayload) {
        try {
            List<Department> departments = new ArrayList<>();

            for (DepartmentPayload payload : departmentsPayload) {
                Department department = new Department();
                department.setName(payload.getName());
                List<Group> groups = new ArrayList<>();
                if (payload.getGroupIds() != null) {
                    for (Long groupId : payload.getGroupIds()) {
                        Group group = new Group();
                        group.setId(groupId);
                        groups.add(group);
                    }
                    department.setGroups(groups);
                }
                departments.add(department);
            }

            departments = departmentRepository.saveAll(departments);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), departments));
        } catch (BadRequestException e) {
            log.error("you are failed to save the department");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> editDepartment(DepartmentPayload departmentPayload) {
        try {
            Department department = new Department(departmentPayload.getId(), departmentPayload.getName());

            List<Group> groups = new ArrayList<>();
            if (departmentPayload.getGroupIds() != null) {
                for (Long groupId : departmentPayload.getGroupIds()) {
                    Group group = new Group();
                    group.setId(groupId);
                    groups.add(group);
                }
                department.setGroups(groups);
            }
            department = departmentRepository.save(department);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), department));
        } catch (BadRequestException e) {
            log.error("you are failed to edit the department");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
        log.info("you deleted a department linked this id: " + id);
    }

    @Override
    public ResponseEntity<?> findAllDepartments() {
        try {
            List<Department> departments = departmentRepository.findAll();
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), departments));
        } catch (ResourceNotFoundException e) {
            log.error("Not resource found");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> findDepartmentById(Long id) {
        try {
            Optional<Department> department = departmentRepository.findById(id);

            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), department));
        } catch (ResourceNotFoundException e) {
            log.error("Not resource found linked this id : " + id);
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    private void department(DepartmentPayload departmentPayload) {

    }
}
