package com.akshay.crud_maping_demo.manytomany.repository;

import com.akshay.crud_maping_demo.manytomany.entity.ManyManyCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManyToManyCourseRepo extends JpaRepository<ManyManyCourse, Long> {

    List<ManyManyCourse> findByManyManyStudents_Id(Long id);

    List<ManyManyCourse> findByManyManyStudents_Name(String name);

    List<ManyManyCourse> findByManyManyStudents_NameIgnoreCase(String name);

    List<ManyManyCourse> findByManyManyStudents_NameContaining(String name);

    List<ManyManyCourse> findByManyManyStudents_IdAndCourseName(Long studentId, String courseName);

    List<ManyManyCourse> findByCourseName(String courseName);
}
