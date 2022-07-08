package com.example.university.service.Impl;

import com.example.university.Repository.GroupRepository;
import com.example.university.Repository.JournalRepository;
import com.example.university.Repository.StudentRepository;
import com.example.university.entity.Group;
import com.example.university.entity.Journal;
import com.example.university.entity.Student;
import com.example.university.entity.Subject;
import com.example.university.exeptions.BadRequestException;
import com.example.university.exeptions.ResourceNotFoundException;
import com.example.university.model.Result;
import com.example.university.payload.GroupPayload;
import com.example.university.service.GroupService;
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
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final JournalRepository journalRepository;


    @Override
    public ResponseEntity<?> createGroup(GroupPayload groupPayload) {
        try{
                Group group = new Group();
                group.setName(groupPayload.getName());

            List<Subject> subjects = new ArrayList<>();
            if(groupPayload.getSubjectIds()!=null) {
                for (Long subjectId : groupPayload.getSubjectIds()) {
                    Subject subject = new Subject();
                    subject.setId(subjectId);
                    subjects.add(subject);
                }
                group.setSubjects(subjects);
            }

            List<Student> students = new ArrayList<>();
            if(groupPayload.getStudentIds()!=null) {
                for (Long studentId : groupPayload.getStudentIds()) {
                    Student student = new Student();
                    student.setId(studentId);
                    if(studentRepository.findById(studentId).isPresent()) {
                        student.setFullName(studentRepository.findById(studentId).get().getFullName());
                        student.setBirthOfDate(studentRepository.findById(studentId).get().getBirthOfDate());
                        student.setAddress(studentRepository.findById(studentId).get().getAddress());
                        student.setContractType(studentRepository.findById(studentId).get().getContractType());
                    }
                    student.setSubjects(subjects);
                    students.add(student);
                }
                studentRepository.saveAll(students);
                group.setStudents(students);
            }
            Journal journal = new Journal();
            group = groupRepository.save(group);
            journal.setGroup(group);
            journal.setSubjects(subjects);
            journalRepository.save(journal);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), group));
        }catch (BadRequestException e){
            log.error("you are failed to save the group");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> editGroup(GroupPayload groupPayload) {
        try{
            Group group = new Group();
            group.setId(groupPayload.getId());
            group.setName(groupPayload.getName());
            List<Student> students = new ArrayList<>();
            if(groupPayload.getStudentIds()!=null) {
                for (Long studentId : groupPayload.getStudentIds()) {
                    Student student = new Student();
                    student.setId(studentId);
                    students.add(student);
                }
                group.setStudents(students);
            }
            List<Subject> subjects = new ArrayList<>();
            if(groupPayload.getSubjectIds()!=null) {
                if(groupPayload.getStudentIds()!=null) {
                    for (Long studentId : groupPayload.getStudentIds()) {
                        Student student = new Student();
                        student.setId(studentId);
                        if(studentRepository.findById(studentId).isPresent()){
                            student.setFullName(studentRepository.findById(studentId).get().getFullName());
                            student.setBirthOfDate(studentRepository.findById(studentId).get().getBirthOfDate());
                            student.setAddress(studentRepository.findById(studentId).get().getAddress());
                            student.setContractType(studentRepository.findById(studentId).get().getContractType());
                            student.setSubjects(subjects);
                        }
                        students.add(student);
                    }
                    studentRepository.saveAll(students);
                    group.setStudents(students);
                }
                group.setSubjects(subjects);
            }
            Journal journal = new Journal();
            group = groupRepository.save(group);
            journal.setId(journalRepository.findJournalByGroupId(group.getId()).getId());
            journal.setGroup(group);
            journal.setSubjects(subjects);
            journalRepository.save(journal);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(), group));
        }catch (BadRequestException e){
            log.error("you are failed to save the group");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> findAllGroups() {
        try {
            List<Group> groups = groupRepository.findAll();
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),groups));
        }catch (ResourceNotFoundException e){
            log.error("Not resource found");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> findGroupById(Long id) {
        try {
            Optional<Group> group = groupRepository.findById(id);

            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),group));
        }catch (ResourceNotFoundException e){
            log.error("Not resource found");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }
}
