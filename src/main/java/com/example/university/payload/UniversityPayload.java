package com.example.university.payload;

import com.example.university.entity.Faculty;
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
public class UniversityPayload {

    private Long id;

    private String name;

    private String openedYear;

    private List<Long> facultyIds;

    private List<Faculty> faculties;

    private Date createdAt;
}
