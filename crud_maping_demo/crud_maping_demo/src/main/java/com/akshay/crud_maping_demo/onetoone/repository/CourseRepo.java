package com.akshay.crud_maping_demo.onetoone.repository;

import com.akshay.crud_maping_demo.onetoone.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Long> {
    Course findByStudent_Id(Long id);

    Course findByStudent_Name(String name);

    Course findByStudent_NameIgnoreCase(String name);

    List<Course> findByStudent_NameContaining(String namePart);

    Course findByStudent_NameAndCourseName(String studentName, String courseName);

    Course findByStudent_IdAndCourseName(Long studentId, String courseName);

    List<Course> findByCourseName(String courseName);
}
