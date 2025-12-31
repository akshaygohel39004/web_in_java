package com.akshay.crud_maping_demo.onetoone.repository;

import com.akshay.crud_maping_demo.onetoone.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StudentRepo extends JpaRepository<Student, UUID> {

    Student findByCourse_Id(UUID courseId);

    List<Student> findByNameIgnoreCase(String name);

    List<Student> findByCourse_CourseName(String courseName);

    List<Student> findByNameContaining(String namePart);


    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Student fetchByIdJPQL(UUID id);

    @Query("SELECT s.course.courseName, COUNT(s.id) FROM Student s GROUP BY s.course.courseName")
    List<Object[]> groupStudentCountByCourse();

    @Query("SELECT s.name, s.course.courseName FROM Student s GROUP BY s.name, s.course.courseName")
    List<Object[]> groupStudentByNameAndCourse();
}
