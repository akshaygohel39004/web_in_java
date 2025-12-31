package com.akshay.crud_maping_demo.manytomany.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "t_course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TCourse {

    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    private List<TStudent> students;
}
