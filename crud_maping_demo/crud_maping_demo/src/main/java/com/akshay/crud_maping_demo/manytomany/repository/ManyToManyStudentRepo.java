package com.akshay.crud_maping_demo.manytomany.repository;

import com.akshay.crud_maping_demo.manytomany.entity.ManyManyStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManyToManyStudentRepo extends JpaRepository<ManyManyStudent, Long> {

    List<ManyManyStudent> findByManyManyCourses_Id(Long id);

    List<ManyManyStudent> findByManyManyCourses_CourseName(String courseName);

    List<ManyManyStudent> findByManyManyCourses_CourseNameAndName(String courseName, String name);
}
