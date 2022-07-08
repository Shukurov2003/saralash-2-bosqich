package com.example.university.service.Impl;

import com.example.university.Repository.UniversityRepository;
import com.example.university.entity.Faculty;
import com.example.university.entity.University;
import com.example.university.exeptions.BadRequestException;
import com.example.university.exeptions.ResourceNotFoundException;
import com.example.university.model.Result;
import com.example.university.payload.UniversityPayload;
import com.example.university.service.UniversityService;
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
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Override
    public ResponseEntity<?> createUniversity(UniversityPayload universityPayload) {
        try{
            University university =  new University();
            university.setName(universityPayload.getName());
            List<Faculty> faculties = new ArrayList<>();
            if(universityPayload.getFacultyIds()!=null) {
                for (Long facultyIds : universityPayload.getFacultyIds()) {
                    Faculty faculty = new Faculty();
                    faculty.setId(facultyIds);
                    faculties.add(faculty);
                }
                university.setFaculties(faculties);
            }

            university = universityRepository.save(university);
           return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),university));
        }catch (BadRequestException e){
            log.error("you are failed to save the university");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    public ResponseEntity<?> createMultipleUniversity(List<UniversityPayload> universityPayload) {
        try{
            List<University>  universities = new ArrayList<>();

            for (UniversityPayload payload : universityPayload) {
                University university = new University();
                university.setName(payload.getName());
                university.setOpenedYear(payload.getOpenedYear());
                List<Faculty> faculties = new ArrayList<>();
                    if (payload.getFacultyIds() != null) {
                        for (Long facultyId : payload.getFacultyIds()) {
                            Faculty faculty = new Faculty();
                            faculty.setId(facultyId);
                            faculties.add(faculty);
                        }
                        university.setFaculties(faculties);
                    }


                universities.add(university);
            }
            universities = universityRepository.saveAll(universities);

            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),universities));
        }catch (BadRequestException e){
            log.error("you are failed to save the faculty");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }



    @Override
    public ResponseEntity<?> editUniversity(UniversityPayload universityPayload) {
        try{
            University university =  new University();
            university.setId(universityPayload.getId());
            university.setName(universityPayload.getName());
            university.setOpenedYear(universityPayload.getOpenedYear());
            System.out.println("bu university:"+university);
            List<Faculty> faculties = new ArrayList<>();
            if(universityPayload.getFacultyIds()!=null) {
                for (Long facultyIds : universityPayload.getFacultyIds()) {
                    Faculty faculty = new Faculty();
                    faculty.setId(facultyIds);
                    faculties.add(faculty);
                }

                university.setFaculties(faculties);
            }
            university = universityRepository.save(university);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),university));
        }catch (BadRequestException e){
            log.error("you are failed to save the university");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public void deleteUniversity(Long id) {
        universityRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> findAllUniversities() {
        try{
            List<University> universities = universityRepository.findAll();
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),universities));
        }catch (ResourceNotFoundException e){
            log.error("Not Resources found ! ");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> findUniversityById(Long id) {
        try{
            Optional<University> university = universityRepository.findById(id);
            return ResponseEntity.ok(Result.ok(HttpStatus.OK.toString(),university));
        }catch (ResourceNotFoundException e){
            log.error("Not Resource found ! ");
            return ResponseEntity.badRequest().body(Result.error(e.getMessage()));
        }
    }
}
