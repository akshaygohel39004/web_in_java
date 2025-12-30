package com.akshay.crud_maping_demo.manytomany.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "many_many_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ManyManyStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<ManyManyCourse> manyManyCourses;
}
