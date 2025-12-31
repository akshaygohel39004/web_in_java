package com.akshay.crud_maping_demo.onetomany.controller;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.onetomany.entity.SStudent;
import com.akshay.crud_maping_demo.onetomany.service.SStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/s-students")
public class SStudentController {

    private final SStudentService studentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SStudent student) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(studentService.create(student));
        } catch (BadRequestException e) {
            return error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(studentService.getById(id));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/jpql/{id}")
    public ResponseEntity<?> getByIdJPQL(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(studentService.getByIdJPQL(id));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-course-id/{courseId}")
    public ResponseEntity<?> getByCourseId(@PathVariable UUID courseId) {
        try {
            return ResponseEntity.ok(studentService.getByCourseId(courseId));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-course-name/{courseName}")
    public ResponseEntity<?> findByCourseName(@PathVariable String courseName) {
        try {
            return ResponseEntity.ok(studentService.findByCourseName(courseName));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(studentService.findByNameIgnoreCase(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{part}")
    public ResponseEntity<?> findByNamePart(@PathVariable String part) {
        try {
            return ResponseEntity.ok(studentService.findByNamePart(part));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/group/count")
    public ResponseEntity<?> groupCount() {
        return ResponseEntity.ok(studentService.groupCountByCourse());
    }

    @GetMapping("/group/name-course")
    public ResponseEntity<?> groupNameCourse() {
        return ResponseEntity.ok(studentService.groupByNameAndCourse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SStudent updated) {
        try {
            return ResponseEntity.ok(studentService.update(id, updated));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            studentService.delete(id);
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
