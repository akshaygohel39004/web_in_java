package com.akshay.crud_maping_demo.onetoone.controller;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.onetoone.entity.Student;
import com.akshay.crud_maping_demo.onetoone.service.FStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/students")
public class FStudentController {

    private final FStudentService studentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student) {
        try {
            Student saved = studentService.createStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (BadRequestException e) {
            return error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(studentService.getStudent(id));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/jpql/{id}")
    public ResponseEntity<?> getByIdJPQL(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(studentService.getStudentJPQL(id));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-course-id/{courseId}")
    public ResponseEntity<?> getByCourseId(@PathVariable UUID courseId) {
        try {
            return ResponseEntity.ok(studentService.getStudentByCourseId(courseId));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> searchByNameIgnoreCase(@PathVariable String name) {
        try {
            return ResponseEntity.ok(studentService.searchByNameIgnoreCase(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{part}")
    public ResponseEntity<?> searchByNamePart(@PathVariable String part) {
        try {
            return ResponseEntity.ok(studentService.searchByNamePart(part));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-course-name/{courseName}")
    public ResponseEntity<?> getByCourseName(@PathVariable String courseName) {
        try {
            return ResponseEntity.ok(studentService.getStudentsByCourseName(courseName));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/group/count-by-course")
    public ResponseEntity<?> groupCountByCourse() {
        return ResponseEntity.ok(studentService.groupStudentCountByCourse());
    }

    @GetMapping("/group/by-name-course")
    public ResponseEntity<?> groupByNameAndCourse() {
        return ResponseEntity.ok(studentService.groupStudentByNameAndCourse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Student updated) {
        try {
            return ResponseEntity.ok(studentService.updateStudent(id, updated));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Map<String, Object>> error(String msg, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", msg);
        return new ResponseEntity<>(body, status);
    }
}
