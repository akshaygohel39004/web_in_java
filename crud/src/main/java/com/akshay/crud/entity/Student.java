package com.akshay.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String studentName;
    private LocalDate DOB;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;



    @Override
    public String toString() {
        return "Student{id=" + id +
                ", name='" + studentName + '\'' +
                ", dob=" + DOB +
                ", courseId=" + (course != null ? course.getId() : null) +
                '}';
    }

}
