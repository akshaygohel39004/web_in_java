package com.akshay.crud_maping_demo.onetomany.controller;

import com.akshay.crud_maping_demo.onetomany.entity.OneManyCourse;
import com.akshay.crud_maping_demo.onetomany.entity.OneManyStudent;
import com.akshay.crud_maping_demo.onetomany.repository.OneToManyCourseRepo;
import com.akshay.crud_maping_demo.onetomany.repository.ManyToOneStudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/one-many")
public class OneToManyController {

    private final OneToManyCourseRepo courseRepo;
    private final ManyToOneStudentRepo studentRepo;

    @PostMapping("/courses")
    public ResponseEntity<OneManyCourse> createCourse(@RequestBody OneManyCourse oneManyCourse){
        return ResponseEntity.ok(courseRepo.save(oneManyCourse));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<OneManyCourse>> getAllCourses(){
        return ResponseEntity.ok(courseRepo.findAll());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<OneManyCourse> getCourseById(@PathVariable Long id){
        return ResponseEntity.of(courseRepo.findById(id));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<OneManyCourse> updateCourse(@PathVariable Long id, @RequestBody OneManyCourse updated){
        return ResponseEntity.of(
                courseRepo.findById(id).map(c -> {
                    c.setCourseName(updated.getCourseName());
                    return courseRepo.save(c);
                })
        );
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        courseRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/students")
    public ResponseEntity<OneManyStudent> createStudent(@RequestBody OneManyStudent oneManyStudent){
        return ResponseEntity.ok(studentRepo.save(oneManyStudent));
    }

    @GetMapping("/students")
    public ResponseEntity<List<OneManyStudent>> getAllStudents(){
        return ResponseEntity.ok(studentRepo.findAll());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<OneManyStudent> getStudent(@PathVariable Long id){
        return ResponseEntity.of(studentRepo.findById(id));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<OneManyStudent> updateStudent(@PathVariable Long id, @RequestBody OneManyStudent updated){
        return ResponseEntity.of(
                studentRepo.findById(id).map(s -> {
                    s.setName(updated.getName());
                    s.setOneManyCourse(updated.getOneManyCourse());
                    return studentRepo.save(s);
                })
        );
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/students/by-course/{courseId}")
    public ResponseEntity<OneManyStudent> getStudentByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(studentRepo.findByOneManyCourse_Id(courseId));
    }

    @GetMapping("/students/by-course-name/{name}")
    public ResponseEntity<List<OneManyStudent>> getStudentByCourseName(@PathVariable String name){
        return ResponseEntity.ok(studentRepo.findByOneManyCourse_CourseName(name));
    }

    @GetMapping("/courses/by-student/{studentId}")
    public ResponseEntity<OneManyCourse> getCourseByStudentId(@PathVariable Long studentId){
        return ResponseEntity.ok(courseRepo.findByOneManyStudents_Id(studentId));
    }

    @GetMapping("/courses/by-student-name/{name}")
    public ResponseEntity<OneManyCourse> getCourseByStudentName(@PathVariable String name){
        return ResponseEntity.ok(courseRepo.findByOneManyStudents_NameIgnoreCase(name));
    }

    @GetMapping("/courses/name/{courseName}")
    public ResponseEntity<List<OneManyCourse>> findCourseByCourseName(@PathVariable String courseName){
        return ResponseEntity.ok(courseRepo.findByCourseName(courseName));
    }
}
