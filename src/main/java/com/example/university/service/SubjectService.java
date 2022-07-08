package com.example.university.service;

import com.example.university.payload.SubjectPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubjectService {
    ResponseEntity<?> createMultipleSubjects(List<SubjectPayload> subjectsPayload);
    ResponseEntity<?> createSubject(SubjectPayload subjectPayload);
    ResponseEntity<?> editSubject(SubjectPayload subjectPayload);
    void deleteSubject(Long id);
    ResponseEntity<?> findAllSubjects();
}
