package com.example.university.service;

import com.example.university.payload.MarkPayload;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JournalService {

    ResponseEntity<?> putTotalMarksToStudents(List<MarkPayload> marksPayload);
    ResponseEntity<?> putTotalMarkToStudent(MarkPayload markPayload);
    ResponseEntity<?> editPutTotalMarksToStudents(List<MarkPayload> marksPayload);
    ResponseEntity<?> editPutTotalMarkToStudent(MarkPayload markPayload);
    ResponseEntity<?> findJournalByGroupId(Long id);

}
