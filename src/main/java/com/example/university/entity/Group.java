package com.example.university.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Student> students;

    @ManyToMany
    private List<Subject> subjects;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
