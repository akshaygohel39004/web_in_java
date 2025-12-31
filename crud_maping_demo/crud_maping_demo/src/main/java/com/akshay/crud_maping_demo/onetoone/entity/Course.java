package com.akshay.crud_maping_demo.onetoone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    private UUID id;


    @NonNull
    private String courseName;

    @JsonBackReference
    @OneToOne(mappedBy = "course")
    private Student student;
}
