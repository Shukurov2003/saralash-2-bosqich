package com.example.university.service.Impl;

import com.example.university.Repository.SubjectRepository;
import com.example.university.entity.Subject;
import com.example.university.exeptions.BadRequestException;
import com.example.university.exeptions.ResourceNotFoundException;
import com.example.university.model.Result;
import com.example.university.payload.SubjectPayload;
import com.example.university.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    @Override
    public ResponseEntity<?> createMultipleSubjects(List<SubjectPayload> subjectsPayload) {
        try{

            List<Subject> subjects = new ArrayList<>();

            for (SubjectPayload payload : subjectsPayload) {
                Subject subject = new Subject();
                subject.setName(payload.getName());
                subjects.add(subject);
            }
            subjects = subjectRepository.saveAll(subjects);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), subjects));

        }catch (BadRequestException e){
            log.error("you are failed to save the subjects");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> createSubject(SubjectPayload subjectPayload) {
        try{
            Subject subject = new Subject();

            subject.setName(subjectPayload.getName());
            subject = subjectRepository.save(subject);

            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), subject));

        }catch (BadRequestException e){
            log.error("you are failed to save the subject");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> editSubject(SubjectPayload subjectPayload) {
        try{

            Subject subject = new Subject();
            subject.setId(subjectPayload.getId());
            subject.setName(subjectPayload.getName());
            subject = subjectRepository.save(subject);

            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), subject));

        }catch (BadRequestException e){
            log.error("you are failed to edit the subject");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public void deleteSubject(Long id) {

        subjectRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> findAllSubjects() {
        try{
            List<Subject> subjects = subjectRepository.findAll();
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),subjects));
        }catch (ResourceNotFoundException e){
            log.error("Not Resource found");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }
}
