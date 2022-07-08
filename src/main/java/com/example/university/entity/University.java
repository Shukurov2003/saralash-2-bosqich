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
@Entity(name = "university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String openedYear;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Faculty> faculties;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
