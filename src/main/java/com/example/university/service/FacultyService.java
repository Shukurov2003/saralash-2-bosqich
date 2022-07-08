package com.example.university.service;

import com.example.university.entity.Faculty;
import com.example.university.payload.FacultyPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FacultyService {

    ResponseEntity<?> createFaculty(FacultyPayload facultyPayload);
    ResponseEntity<?> createMultipleFaculty(List<FacultyPayload> facultiesPayload);
    ResponseEntity<?> editFaculty(FacultyPayload facultyPayload);
    void  deleteFaculty(Long id);
    ResponseEntity<?> findAllFaculties();
    ResponseEntity<?> findFacultyById(Long id);
}
