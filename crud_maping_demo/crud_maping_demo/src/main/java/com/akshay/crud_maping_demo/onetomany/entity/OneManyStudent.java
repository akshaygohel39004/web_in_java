package com.akshay.crud_maping_demo.onetomany.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "one_many_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class OneManyStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private OneManyCourse oneManyCourse;
}
