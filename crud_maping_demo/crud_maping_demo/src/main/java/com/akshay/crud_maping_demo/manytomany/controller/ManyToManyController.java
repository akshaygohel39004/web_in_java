package com.akshay.crud_maping_demo.manytomany.controller;

import com.akshay.crud_maping_demo.manytomany.entity.ManyManyCourse;
import com.akshay.crud_maping_demo.manytomany.entity.ManyManyStudent;
import com.akshay.crud_maping_demo.manytomany.repository.ManyToManyCourseRepo;
import com.akshay.crud_maping_demo.manytomany.repository.ManyToManyStudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/many-many")
public class ManyToManyController {

    private final ManyToManyCourseRepo courseRepo;
    private final ManyToManyStudentRepo studentRepo;

    @PostMapping("/courses")
    public ResponseEntity<ManyManyCourse> createCourse(@RequestBody ManyManyCourse course){
        return ResponseEntity.ok(courseRepo.save(course));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<ManyManyCourse>> getAllCourses(){
        return ResponseEntity.ok(courseRepo.findAll());
    }

    @PostMapping("/students")
    public ResponseEntity<ManyManyStudent> createStudent(@RequestBody ManyManyStudent student){
        return ResponseEntity.ok(studentRepo.save(student));
    }

    @GetMapping("/students")
    public ResponseEntity<List<ManyManyStudent>> getAllStudents(){
        return ResponseEntity.ok(studentRepo.findAll());
    }

    @GetMapping("/students/by-course/{courseId}")
    public ResponseEntity<List<ManyManyStudent>> getStudentsByCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(studentRepo.findByManyManyCourses_Id(courseId));
    }

    @GetMapping("/students/by-course-name/{name}")
    public ResponseEntity<List<ManyManyStudent>> getStudentsByCourseName(@PathVariable String name){
        return ResponseEntity.ok(studentRepo.findByManyManyCourses_CourseName(name));
    }

    @GetMapping("/courses/by-student/{studentId}")
    public ResponseEntity<List<ManyManyCourse>> getCoursesByStudentId(@PathVariable Long studentId){
        return ResponseEntity.ok(courseRepo.findByManyManyStudents_Id(studentId));
    }

    @GetMapping("/courses/by-student-name/{name}")
    public ResponseEntity<List<ManyManyCourse>> getCoursesByStudentName(@PathVariable String name){
        return ResponseEntity.ok(courseRepo.findByManyManyStudents_NameIgnoreCase(name));
    }

    @GetMapping("/courses/name/{courseName}")
    public ResponseEntity<List<ManyManyCourse>> findCourseByCourseName(@PathVariable String courseName){
        return ResponseEntity.ok(courseRepo.findByCourseName(courseName));
    }
}
