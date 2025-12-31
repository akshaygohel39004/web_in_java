package com.akshay.crud_maping_demo.onetomany.controller;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.onetomany.entity.SCourse;
import com.akshay.crud_maping_demo.onetomany.service.SCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/s-courses")
public class SCourseController {

    private final SCourseService courseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SCourse course) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(courseService.create(course));
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


    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(courseService.findByCourseName(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/by-student-id/{studentId}")
    public ResponseEntity<?> getByStudentId(@PathVariable UUID studentId) {
        try {
            return ResponseEntity.ok(courseService.getByStudentId(studentId));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/by-student-name/{name}")
    public ResponseEntity<?> getByStudentName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(courseService.getByStudentName(name));
        } catch (NotFoundException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/group/count")
    public ResponseEntity<?> countGroup() {
        return ResponseEntity.ok(courseService.countByCourseGroup());

    }


    @GetMapping("/group/name-id")
    public ResponseEntity<?> groupByNameAndId() {
        return ResponseEntity.ok(courseService.groupByNameAndId());

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SCourse updated) {
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
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", msg);
        return new ResponseEntity<>(body, status);
    }
}
