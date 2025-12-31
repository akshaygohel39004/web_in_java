package com.akshay.crud_maping_demo.onetoone.controller;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.onetoone.entity.Course;
import com.akshay.crud_maping_demo.onetoone.service.FCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/courses")
public class FCourseController {

    private final FCourseService courseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Course course) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(courseService.createCourse(course));
        } catch (BadRequestException e) {
            return error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(courseService.getCourseById(id));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/jpql/{id}")
    public ResponseEntity<?> getByIdJPQL(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(courseService.getCourseByIdJPQL(id));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(courseService.getCourseByName(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{part}")
    public ResponseEntity<?> searchByNamePart(@PathVariable String part) {
        try {
            return ResponseEntity.ok(courseService.searchCourseByNamePart(part));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-student-id/{studentId}")
    public ResponseEntity<?> getByStudentId(@PathVariable UUID studentId) {
        try {
            return ResponseEntity.ok(courseService.getCourseByStudentId(studentId));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-student-name/{name}")
    public ResponseEntity<?> getByStudentName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(courseService.getCourseByStudentName(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/group/count-students")
    public ResponseEntity<?> countGroup() {
        return ResponseEntity.ok(courseService.countStudentsGroupByCourse());
    }

    @GetMapping("/group/by-name-id")
    public ResponseEntity<?> groupByNameAndId() {
        return ResponseEntity.ok(courseService.groupCourseByNameAndId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Course updated) {
        try {
            return ResponseEntity.ok(courseService.updateCourse(id, updated));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Map<String, Object>> error(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", message);
        return new ResponseEntity<>(body, status);
    }
}
