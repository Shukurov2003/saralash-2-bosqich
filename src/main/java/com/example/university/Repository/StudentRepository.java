package com.example.university.Repository;

import com.example.university.entity.Mark;
import com.example.university.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findStudentByFullName(String name);

    @Query(
//            "UPDATE student s set s.marks=?1 where s.id=?2"
            "update student s set s.marks=?1"
    )
    void setMarkToStudent(List<Mark> marks);
}
