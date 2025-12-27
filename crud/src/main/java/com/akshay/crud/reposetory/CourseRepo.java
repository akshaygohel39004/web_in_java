package com.akshay.crud.reposetory;

import com.akshay.crud.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends CrudRepository<Course,Integer> {

}
