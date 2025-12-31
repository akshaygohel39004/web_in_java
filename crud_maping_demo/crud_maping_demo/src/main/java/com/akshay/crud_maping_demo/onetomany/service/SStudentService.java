package com.akshay.crud_maping_demo.onetomany.service;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.onetomany.entity.SStudent;
import com.akshay.crud_maping_demo.onetomany.repository.SStudentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SStudentService {

    private final SStudentRepo studentRepo;

    @Transactional
    public SStudent create(SStudent student) {
        if (student.getName() == null || student.getName().isBlank())
            throw new BadRequestException("Student name cannot be empty");
        return studentRepo.save(student);
    }

    public List<SStudent> getAll() {
        return studentRepo.findAll();
    }

    public SStudent getById(UUID id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

    public SStudent getByIdJPQL(UUID id) {
        SStudent s = studentRepo.fetchByIdJPQL(id);
        if (s == null) throw new NotFoundException("Student not found: " + id);
        return s;
    }

    public SStudent getByCourseId(UUID courseId) {
        SStudent s = studentRepo.findByCourse_Id(courseId);
        if (s == null) throw new NotFoundException("Student not found for course id: " + courseId);
        return s;
    }

    public List<SStudent> findByCourseName(String courseName) {
        List<SStudent> list = studentRepo.findByCourse_CourseName(courseName);
        if (list.isEmpty()) throw new NotFoundException("No students found for course: " + courseName);
        return list;
    }

    public List<SStudent> findByNameIgnoreCase(String name) {
        List<SStudent> list = studentRepo.findByNameIgnoreCase(name);
        if (list.isEmpty()) throw new NotFoundException("No students found with name: " + name);
        return list;
    }

    public List<SStudent> findByNamePart(String part) {
        List<SStudent> list = studentRepo.findByNameContaining(part);
        if (list.isEmpty()) throw new NotFoundException("No students found containing: " + part);
        return list;
    }

    public List<Object[]> groupCountByCourse() {
        return studentRepo.groupStudentCountByCourse();
    }

    public List<Object[]> groupByNameAndCourse() {
        return studentRepo.groupStudentByNameAndCourse();
    }

    @Transactional
    public SStudent update(UUID id, SStudent updated) {
        return studentRepo.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setCourse(updated.getCourse());
                    return studentRepo.save(existing);
                })
                .orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

    @Transactional
    public void delete(UUID id) {
        if (!studentRepo.existsById(id))
            throw new NotFoundException("Student not found: " + id);
        studentRepo.deleteById(id);
    }
}
