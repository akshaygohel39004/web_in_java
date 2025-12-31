package com.akshay.crud_maping_demo.onetomany.repository;

import com.akshay.crud_maping_demo.onetomany.entity.SStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SStudentRepo extends JpaRepository<SStudent, UUID> {

    SStudent findByCourse_Id(UUID id);

    List<SStudent> findByCourse_CourseName(String courseName);

    List<SStudent> findByNameIgnoreCase(String name);

    List<SStudent> findByNameContaining(String part);

    @Query("SELECT s FROM SStudent s WHERE s.id = :id")
    SStudent fetchByIdJPQL(UUID id);

    @Query("SELECT s.course.courseName, COUNT(s.id) FROM SStudent s GROUP BY s.course.courseName")
    List<Object[]> groupStudentCountByCourse();

    @Query("SELECT s.name, s.course.courseName FROM SStudent s GROUP BY s.name, s.course.courseName")
    List<Object[]> groupStudentByNameAndCourse();
}
