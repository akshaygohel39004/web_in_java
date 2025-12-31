package com.akshay.crud_maping_demo.onetomany.repository;

import com.akshay.crud_maping_demo.onetomany.entity.SCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SCourseRepo extends JpaRepository<SCourse, UUID> {

    SCourse findByStudents_Id(UUID id);

    SCourse findByStudents_Name(String name);

    SCourse findByStudents_NameIgnoreCase(String name);


    List<SCourse> findByCourseName(String courseName);

    @Query("SELECT c FROM SCourse c WHERE c.id = :id")
    SCourse fetchByIdJPQL(UUID id);

    @Query("SELECT c.courseName, COUNT(c.students) FROM SCourse c GROUP BY c.courseName")
    List<Object[]> countStudentsGroupByCourse();

    @Query("SELECT c.courseName, c.id FROM SCourse c GROUP BY c.courseName, c.id")
    List<Object[]> groupCourseByNameAndId();
}
