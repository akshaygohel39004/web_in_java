package com.akshay.crud_maping_demo.manytomany.controller;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.manytomany.entity.TStudent;
import com.akshay.crud_maping_demo.manytomany.service.TStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/t-students")
public class TStudentController {

    private final TStudentService studentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TStudent student) {
        try {
//            log.info(student.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(student));
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

    @GetMapping("/course-id/{courseId}")
    public ResponseEntity<?> getByCourseId(@PathVariable UUID courseId) {
        try {
            return ResponseEntity.ok(studentService.getByCourseId(courseId));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/course-name/{courseName}")

    public ResponseEntity<?> getByCourseName(@PathVariable String courseName) {
        try {
            return ResponseEntity.ok(studentService.getByCourseName(courseName));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByNameIgnoreCase(@PathVariable String name) {
        try {
            return ResponseEntity.ok(studentService.getByNameIgnoreCase(name));

        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> getByNameContaining(@PathVariable String keyword) {
        try {
            return ResponseEntity.ok(studentService.getByNameContaining(keyword));
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
        return ResponseEntity.ok(studentService.groupStudentByNameAndCourse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TStudent updated) {
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
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("error", msg);
        return new ResponseEntity<>(map, status);
    }
}
