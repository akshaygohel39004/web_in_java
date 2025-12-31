package com.akshay.crud_maping_demo.manytomany.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "t_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TStudent {

    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(
            name = "t_student_course_map",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<TCourse> courses;
}
