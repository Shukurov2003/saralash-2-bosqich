package com.example.university.service;

import com.example.university.payload.UniversityPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UniversityService {


    ResponseEntity<?> createUniversity(UniversityPayload universityPayload);
    ResponseEntity<?> createMultipleUniversity(List<UniversityPayload> universityPayload);
    ResponseEntity<?> editUniversity(UniversityPayload universityPayload);
    void deleteUniversity(Long id);
    ResponseEntity<?> findAllUniversities();
    ResponseEntity<?> findUniversityById(Long id);
}
