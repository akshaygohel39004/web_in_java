package com.akshay.crud_maping_demo.onetoone.repository;

import com.akshay.crud_maping_demo.onetoone.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {
    Student findByCourse_Id(Long id);
    List<Student> findByCourse_CourseName(String courseName);
    List<Student> findByCourse_CourseNameAndName(String courseName, String name);

}
