package com.example.university.service.Impl;

import com.example.university.Repository.*;
import com.example.university.entity.*;
import com.example.university.exeptions.BadRequestException;
import com.example.university.exeptions.ResourceNotFoundException;
import com.example.university.model.Result;
import com.example.university.payload.MarkPayload;
import com.example.university.service.JournalService;
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
public class JournalServiceImpl implements JournalService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;
    private final JournalRepository journalRepository;
    @Override
    public ResponseEntity<?> putTotalMarksToStudents(List<MarkPayload> marksPayload) {
        try {
            List<Mark> marks = new ArrayList<>();
            for (MarkPayload payload: marksPayload){
                Mark mark = new Mark();
                Student student = studentRepository.findById(payload.getStudentId()).orElseThrow(()->new ResourceNotFoundException("Not found"));
                mark.setStudent(student);
                Subject subject = subjectRepository.findById(payload.getSubjectId()).orElseThrow(()->new ResourceNotFoundException("Not found"));
                mark.setSubject(subject);
                mark.setMark(payload.getMark());
            }
            marks = markRepository.saveAll(marks);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),marks));
        }catch (BadRequestException e){
            log.error("you are failed to put Marks to sutdents");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> putTotalMarkToStudent(MarkPayload markPayload) {
        try {
            Mark mark = new Mark();
            Student student = studentRepository.findById(markPayload.getStudentId()).orElseThrow(()->new ResourceNotFoundException("Not found"));
            mark.setStudent(student);
            Subject subject = subjectRepository.findById(markPayload.getSubjectId()).orElseThrow(()->new ResourceNotFoundException("Not found"));
            mark.setSubject(subject);
            mark.setMark(markPayload.getMark());
            mark = markRepository.save(mark);
            List<Mark> marks = new ArrayList<>();
            marks.add(mark);
                Student student1 = new Student();
                student1.setMarks(marks);
                studentRepository.setMarkToStudent(marks);

            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),mark));
        }catch (BadRequestException e){
            log.error("you are failed to put Mark to a student");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> editPutTotalMarksToStudents(List<MarkPayload> marksPayload) {
        try {
            List<Mark> marks = new ArrayList<>();
            for (MarkPayload payload: marksPayload){
                Mark mark = new Mark();
                mark.setId(payload.getId());
                Student student = studentRepository.findById(payload.getStudentId()).orElseThrow(()->new ResourceNotFoundException("Not found"));
                mark.setStudent(student);
                Subject subject = subjectRepository.findById(payload.getSubjectId()).orElseThrow(()->new ResourceNotFoundException("Not found"));
                mark.setSubject(subject);
                mark.setMark(payload.getMark());
            }
            marks = markRepository.saveAll(marks);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),marks));
        }catch (BadRequestException e){
            log.error("you are failed to put Marks to sutdents");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> editPutTotalMarkToStudent(MarkPayload markPayload) {
        try {
            Mark mark = new Mark();
            mark.setId(markPayload.getId());
            Student student = studentRepository.findById(markPayload.getStudentId()).orElseThrow(()->new ResourceNotFoundException("Not found"));
            mark.setStudent(student);
            Subject subject = subjectRepository.findById(markPayload.getSubjectId()).orElseThrow(()->new ResourceNotFoundException("Not found"));
            mark.setSubject(subject);
            mark.setMark(markPayload.getMark());
            mark = markRepository.save(mark);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),mark));
        }catch (BadRequestException e){
            log.error("you are failed to put Mark to a student");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }


    @Override
    public ResponseEntity<?> findJournalByGroupId(Long id) {
        try{
            Journal journal = journalRepository.findJournalByGroupId(id);
            System.out.println("this is:"+journal);
            if(journal==null){
                return ResponseEntity.badRequest().body(Result.error("Not Resource found!"));
            }
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), journal));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }
}
