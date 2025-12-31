package com.akshay.crud_maping_demo.onetoone.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private UUID id;


    @NonNull
    private String name;


    @JsonManagedReference
    @NonNull
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
