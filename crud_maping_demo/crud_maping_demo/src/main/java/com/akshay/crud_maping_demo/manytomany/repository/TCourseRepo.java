package com.akshay.crud_maping_demo.manytomany.repository;

import com.akshay.crud_maping_demo.manytomany.entity.TCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TCourseRepo extends JpaRepository<TCourse, UUID> {

    TCourse findByStudents_Id(UUID studentId);

    TCourse findByStudents_Name(String name);

    TCourse findByStudents_NameIgnoreCase(String name);

    List<TCourse> findByStudents_NameContaining(String keyword);

    List<TCourse> findByCourseName(String courseName);


    @Query("SELECT c FROM TCourse c WHERE c.id = :id")
    TCourse fetchByIdJPQL(UUID id);

    @Query("SELECT c.courseName, COUNT(s.id) FROM TCourse c JOIN c.students s GROUP BY c.courseName")
    List<Object[]> countStudentsGroupByCourse();

    @Query("SELECT c.courseName, c.id FROM TCourse c GROUP BY c.courseName, c.id")
    List<Object[]> groupCourseByNameAndId();
}
