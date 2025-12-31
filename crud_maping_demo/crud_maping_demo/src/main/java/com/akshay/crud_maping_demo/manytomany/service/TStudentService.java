package com.akshay.crud_maping_demo.manytomany.service;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.manytomany.entity.TStudent;
import com.akshay.crud_maping_demo.manytomany.repository.TStudentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TStudentService {

    private final TStudentRepo studentRepo;

    @Transactional
    public TStudent create(TStudent student) {

        log.info(student.getCourses().toString());
        if (student.getName() == null || student.getName().isBlank())
            throw new BadRequestException("Student name cannot be empty");
        return studentRepo.save(student);
    }

    public List<TStudent> getAll() {
        return studentRepo.findAll();
    }

    public TStudent getById(UUID id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

    public TStudent getByIdJPQL(UUID id) {
        TStudent s = studentRepo.fetchByIdJPQL(id);
        if (s == null) throw new NotFoundException("Student not found: " + id);
        return s;
    }

    public TStudent getByCourseId(UUID courseId) {
        TStudent s = studentRepo.findByCourses_Id(courseId);
        if (s == null) throw new NotFoundException("No student found for course id: " + courseId);
        return s;
    }

    public List<TStudent> getByCourseName(String courseName) {
        List<TStudent> list = studentRepo.findByCourses_CourseName(courseName);
        if (list.isEmpty()) throw new NotFoundException("No students found for course: " + courseName);
        return list;
    }

    public List<TStudent> getByNameIgnoreCase(String name) {
        List<TStudent> list = studentRepo.findByNameIgnoreCase(name);
        if (list.isEmpty()) throw new NotFoundException("No students found: " + name);
        return list;
    }

    public List<TStudent> getByNameContaining(String name) {
        List<TStudent> list = studentRepo.findByNameContaining(name);
        if (list.isEmpty()) throw new NotFoundException("No students found for: " + name);
        return list;
    }

    public List<Object[]> groupCountByCourse() {
        return studentRepo.groupStudentCountByCourse();
    }

    public List<Object[]> groupStudentByNameAndCourse() {
        return studentRepo.groupStudentByNameAndCourse();
    }

    @Transactional
    public TStudent update(UUID id, TStudent updated) {
        return studentRepo.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setCourses(updated.getCourses());
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
