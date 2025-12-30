package com.akshay.crud_maping_demo.onetoone.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.akshay.crud_maping_demo.onetoone.entity.Course;
import com.akshay.crud_maping_demo.onetoone.entity.Student;
import com.akshay.crud_maping_demo.onetoone.repository.CourseRepo;
import com.akshay.crud_maping_demo.onetoone.repository.StudentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class OneToOneController {

    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseRepo.save(course));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseRepo.findAll());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        return ResponseEntity.of(courseRepo.findById(id));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updated){
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
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentRepo.save(student));
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentRepo.findAll());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        return ResponseEntity.of(studentRepo.findById(id));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updated){
        return ResponseEntity.of(
                studentRepo.findById(id).map(s -> {
                    s.setName(updated.getName());
                    s.setCourse(updated.getCourse());
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
    public ResponseEntity<Student> getStudentByCourseId(@PathVariable Long courseId){
        return ResponseEntity.ok(studentRepo.findByCourse_Id(courseId));
    }

    @GetMapping("/students/by-course-name/{name}")
    public ResponseEntity<List<Student>> getStudentByCourseName(@PathVariable String name){
        return ResponseEntity.ok(studentRepo.findByCourse_CourseName(name));
    }

    @GetMapping("/courses/by-student/{studentId}")
    public ResponseEntity<Course> getCourseByStudentId(@PathVariable Long studentId){
        return ResponseEntity.ok(courseRepo.findByStudent_Id(studentId));
    }

    @GetMapping("/courses/by-student-name/{name}")
    public ResponseEntity<Course> getCourseByStudentName(@PathVariable String name){
        return ResponseEntity.ok(courseRepo.findByStudent_NameIgnoreCase(name));
    }

    @GetMapping("/courses/name/{courseName}")
    public ResponseEntity<List<Course>> findCourseByCourseName(@PathVariable String courseName){
        return ResponseEntity.ok(courseRepo.findByCourseName(courseName));
    }

}
