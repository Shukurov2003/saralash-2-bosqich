package com.example.university.payload;


import com.example.university.entity.positionType.ContractType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentPayload {

    private Long id;

    private String fullName;

    private String birthOfDate;

    private String address;

    private List<SubjectPayload> subjects;

    @Enumerated(value = EnumType.STRING)
    private ContractType contractType;

    private Date createdAt;
}
