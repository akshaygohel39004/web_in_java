package com.akshay.crud_maping_demo.onetomany.repository;

import com.akshay.crud_maping_demo.onetomany.entity.OneManyCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface OneToManyCourseRepo extends JpaRepository<OneManyCourse, Long> {

    OneManyCourse findByOneManyStudents_Id(Long id);

    OneManyCourse findByOneManyStudents_Name(String name);

    OneManyCourse findByOneManyStudents_NameIgnoreCase(String name);

    List<OneManyCourse> findByOneManyStudents_NameContaining(String namePart);

    OneManyCourse findByOneManyStudents_NameAndCourseName(String studentName, String courseName);

    OneManyCourse findByOneManyStudents_IdAndCourseName(Long studentId, String courseName);

    List<OneManyCourse> findByCourseName(String courseName);
}
