package com.example.university.service.Impl;

import com.example.university.Repository.FacultyRepository;
import com.example.university.Repository.UniversityRepository;
import com.example.university.entity.Department;
import com.example.university.entity.Faculty;
import com.example.university.exeptions.BadRequestException;
import com.example.university.exeptions.ResourceNotFoundException;
import com.example.university.model.Result;
import com.example.university.payload.FacultyPayload;
import com.example.university.service.FacultyService;
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
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;


    @Override
    public ResponseEntity<?> createFaculty(FacultyPayload facultyPayload) {

        try{
            Faculty faculty = new Faculty();
            faculty.setName(facultyPayload.getName());
            List<Department> departments = new ArrayList<>();
            if(facultyPayload.getDepartmentIds()!=null) {
                for (Long departmentId : facultyPayload.getDepartmentIds()) {
                    Department department = new Department();
                    department.setId(departmentId);
                    departments.add(department);
                }
                faculty.setDepartments(departments);
            }

            faculty = facultyRepository.save(faculty);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), faculty));
        }catch (BadRequestException e){
            log.error("you are failed to save the faculty");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> createMultipleFaculty(List<FacultyPayload> facultiesPayload){
        try{
            List<Faculty> faculties = new ArrayList<>();

            for (FacultyPayload payload : facultiesPayload) {

                Faculty faculty = new Faculty();
                faculty.setName(payload.getName());

                List<Department> departments = new ArrayList<>();
                if(payload.getDepartmentIds()!=null) {
                    for (Long departmentId : payload.getDepartmentIds()) {
                        Department department = new Department();
                        department.setId(departmentId);
                        departments.add(department);
                    }
                    faculty.setDepartments(departments);
                }
                faculties.add(faculty);
            }

            faculties = facultyRepository.saveAll(faculties);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), faculties));
        }catch (BadRequestException e){
            log.error("you are failed to save the faculties");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }


    @Override
    public ResponseEntity<?> editFaculty(FacultyPayload facultyPayload) {
        try{
            Faculty faculty = new Faculty();
            faculty.setId(facultyPayload.getId());
            faculty.setName(facultyPayload.getName());
            List<Department> departments = new ArrayList<>();
            if(facultyPayload.getDepartmentIds()!=null) {
                for (Long departmentId : facultyPayload.getDepartmentIds()) {
                    Department department = new Department();
                    department.setId(departmentId);
                    departments.add(department);
                }
                faculty.setDepartments(departments);
            }
            faculty = facultyRepository.save(faculty);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), faculty));
        }catch (BadRequestException e){
            log.error("you are failed to edit the faculty");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> findAllFaculties() {
        try {
            List<Faculty> faculties =  facultyRepository.findAll();
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),faculties));
        }catch (ResourceNotFoundException e){
            log.error("Not resource found");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> findFacultyById(Long id) {
        try {

            Optional<Faculty> faculty = facultyRepository.findById(id);

            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),faculty));
        }catch (ResourceNotFoundException e){
            log.error("Not resource found linked this id : "+id);
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }
}
