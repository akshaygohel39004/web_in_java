package com.akshay.crud_maping_demo.onetoone.repository;

import com.akshay.crud_maping_demo.onetoone.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CourseRepo extends JpaRepository<Course, UUID> {

    Course findByCourseName(String courseName);

    List<Course> findByCourseNameContaining(String namePart);

    Course findByStudent_Id(UUID studentId);

    Course findByStudent_NameIgnoreCase(String studentName);


    @Query("SELECT c FROM Course c WHERE c.id = :id")
    Course fetchByIdJPQL(UUID id);

    @Query("SELECT c.courseName, COUNT(c.student.id) FROM Course c GROUP BY c.courseName")
    List<Object[]> countStudentsGroupByCourse();

    @Query("SELECT c.courseName, c.id FROM Course c GROUP BY c.courseName, c.id")
    List<Object[]> groupCourseByNameAndId();
}
