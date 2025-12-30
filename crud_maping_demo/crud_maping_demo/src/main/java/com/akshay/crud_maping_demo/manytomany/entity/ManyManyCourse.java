package com.akshay.crud_maping_demo.manytomany.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "many_many_course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ManyManyCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String courseName;

    @JsonManagedReference
    @ManyToMany(mappedBy = "manyManyCourses")
    private List<ManyManyStudent> manyManyStudents;
}
