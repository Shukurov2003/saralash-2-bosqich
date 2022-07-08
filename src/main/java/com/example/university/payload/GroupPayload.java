package com.example.university.payload;

import com.example.university.entity.Student;
import com.example.university.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupPayload {

    private Long id;

    private String name;

    private List<Long> studentIds;

    private List<Long> subjectIds;

    private List<Subject> subjects;

    private List<Student> students;


    private Date createdAt;
}
