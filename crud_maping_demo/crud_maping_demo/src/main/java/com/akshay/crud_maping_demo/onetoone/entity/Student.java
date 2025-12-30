package com.akshay.crud_maping_demo.onetoone.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;



    @JsonManagedReference
    @NonNull
    @OneToOne
    @JoinColumn(name ="course_id")
    private Course course;


}
