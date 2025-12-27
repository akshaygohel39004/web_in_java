package com.akshay.crud.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String courseName;


    @OneToOne(mappedBy = "course")
    private Student student;

    @Override
    public String toString() {
        return "Course{id=" + id +
                ", name='" + courseName + '\'' +
                ", studentId=" + (student != null ? student.getId() : null) +
                '}';
    }

}
