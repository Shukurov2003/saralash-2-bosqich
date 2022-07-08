package com.example.university.payload;


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
public class DepartmentPayload {

    private Long id;

    private String name;

    private List<Long> groupIds;

    private List<GroupPayload> groups;

    private Date createdAt;
}
