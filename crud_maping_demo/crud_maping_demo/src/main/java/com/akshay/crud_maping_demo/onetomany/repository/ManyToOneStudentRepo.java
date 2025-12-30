package com.akshay.crud_maping_demo.onetomany.repository;

import com.akshay.crud_maping_demo.onetomany.entity.OneManyStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManyToOneStudentRepo extends JpaRepository<OneManyStudent, Long> {

    OneManyStudent findByOneManyCourse_Id(Long id);

    List<OneManyStudent> findByOneManyCourse_CourseName(String courseName);

    List<OneManyStudent> findByOneManyCourse_CourseNameAndName(String courseName, String name);
}
