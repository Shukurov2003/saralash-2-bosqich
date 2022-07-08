package com.example.university.entity;

import com.example.university.entity.positionType.ContractType;
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
@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String birthOfDate;

    private String address;

    @OneToMany
    private List<Mark> marks;

    @ManyToMany
    private List<Subject> subjects;


    @Enumerated(value = EnumType.STRING)
    private ContractType contractType;


    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public Student(String fullName, String birthOfDate,String address,ContractType contractType){

        this.fullName = fullName;
        this.birthOfDate = birthOfDate;
        this.address = address;
        this.contractType = contractType;
    }
    public Student(Long id,String fullName, String birthOfDate,String address,ContractType contractType){

        this.fullName = fullName;
        this.birthOfDate = birthOfDate;
        this.address = address;
        this.contractType = contractType;
        this.id = id;
    }

    public Student (List<Mark> marks){
        this.marks = marks;
    }


}
