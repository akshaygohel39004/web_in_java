package com.akshay.crud.reposetory;

import com.akshay.crud.entity.Course;
import com.akshay.crud.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

}
