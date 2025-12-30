package com.akshay.crud_maping_demo.onetomany.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "one_many_course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class OneManyCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String courseName;

    @JsonManagedReference
    @OneToMany(mappedBy = "oneManyCourse", cascade = CascadeType.ALL)
    private List<OneManyStudent> oneManyStudents;
}
