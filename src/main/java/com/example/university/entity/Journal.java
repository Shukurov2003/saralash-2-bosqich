package com.example.university.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Subject>  subjects;

    @OneToOne(fetch = FetchType.LAZY)
    private Group group;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
