package com.akshay.crud_maping_demo.manytomany.repository;

import com.akshay.crud_maping_demo.manytomany.entity.TStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TStudentRepo extends JpaRepository<TStudent, UUID> {

    TStudent findByCourses_Id(UUID courseId);

    List<TStudent> findByCourses_CourseName(String courseName);

    List<TStudent> findByNameIgnoreCase(String name);

    List<TStudent> findByNameContaining(String keyword);


    @Query("SELECT s FROM TStudent s WHERE s.id = :id")
    TStudent fetchByIdJPQL(UUID id);

    @Query("SELECT s.courses, COUNT(s.id) FROM TStudent s JOIN s.courses c GROUP BY c.courseName")
    List<Object[]> groupStudentCountByCourse();

    @Query("SELECT s.name, c.courseName FROM TStudent s JOIN s.courses c GROUP BY s.name, c.courseName")
    List<Object[]> groupStudentByNameAndCourse();
}
