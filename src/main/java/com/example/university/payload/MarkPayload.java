package com.example.university.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MarkPayload {

    private Long id;

    private byte mark;

    private Long subjectId;

    private Long studentId;

    private Date createdAt;

}
