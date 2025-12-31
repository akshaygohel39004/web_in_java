package com.akshay.crud_maping_demo.manytomany.controller;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.manytomany.entity.TCourse;
import com.akshay.crud_maping_demo.manytomany.service.TCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/t-courses")
public class TCourseController {

    private final TCourseService courseService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody TCourse course) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(course));
        } catch (BadRequestException e) {
            return error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(courseService.getById(id));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/jpql/{id}")
    public ResponseEntity<?> getByIdJPQL(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(courseService.getByIdJPQL(id));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student-id/{studentId}")
    public ResponseEntity<?> getByStudentId(@PathVariable UUID studentId) {
        try {
            return ResponseEntity.ok(courseService.getByStudentId(studentId));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student-name/{name}")
    public ResponseEntity<?> getByStudentName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(courseService.getByStudentName(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student-name-like/{name}")
    public ResponseEntity<?> getByStudentNameLike(@PathVariable String name) {
        try {
            return ResponseEntity.ok(courseService.getByStudentNameLike(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/course-name/{name}")
    public ResponseEntity<?> getByCourseName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(courseService.getByCourseName(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/group/count")
    public ResponseEntity<?> groupCount() {
        return ResponseEntity.ok(courseService.groupCount());
    }

    @GetMapping("/group/name-id")
    public ResponseEntity<?> groupNameAndId() {
        return ResponseEntity.ok(courseService.groupNameAndId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TCourse updated) {
        try {
            return ResponseEntity.ok(courseService.update(id, updated));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Map<String, Object>> error(String msg, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("error", msg);
        return new ResponseEntity<>(map, status);
    }
}
