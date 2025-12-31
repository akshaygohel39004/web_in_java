package com.akshay.crud_maping_demo.manytomany.service;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.manytomany.entity.TCourse;
import com.akshay.crud_maping_demo.manytomany.repository.TCourseRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TCourseService {

    private final TCourseRepo courseRepo;

    @Transactional
    public TCourse create(TCourse course) {
        if (course.getCourseName() == null || course.getCourseName().isBlank())
            throw new BadRequestException("Course name cannot be empty");
        return courseRepo.save(course);
    }

    public List<TCourse> getAll() {
        return courseRepo.findAll();
    }

    public TCourse getById(UUID id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found: " + id));
    }

    public TCourse getByIdJPQL(UUID id) {
        TCourse c = courseRepo.fetchByIdJPQL(id);
        if (c == null) throw new NotFoundException("Course not found: " + id);
        return c;
    }

    public TCourse getByStudentId(UUID studentId) {
        TCourse c = courseRepo.findByStudents_Id(studentId);
        if (c == null) throw new NotFoundException("No course found for student id: " + studentId);
        return c;
    }

    public TCourse getByStudentName(String name) {
        TCourse c = courseRepo.findByStudents_NameIgnoreCase(name);
        if (c == null) throw new NotFoundException("No course found for student: " + name);
        return c;
    }

    public List<TCourse> getByStudentNameLike(String name) {
        List<TCourse> list = courseRepo.findByStudents_NameContaining(name);
        if (list.isEmpty()) throw new NotFoundException("No course found for: " + name);
        return list;
    }

    public List<TCourse> getByCourseName(String name) {
        List<TCourse> list = courseRepo.findByCourseName(name);
        if (list.isEmpty()) throw new NotFoundException("No course found: " + name);
        return list;
    }

    public List<Object[]> groupCount() {
        return courseRepo.countStudentsGroupByCourse();
    }

    public List<Object[]> groupNameAndId() {
        return courseRepo.groupCourseByNameAndId();
    }

    @Transactional
    public TCourse update(UUID id, TCourse updated) {
        return courseRepo.findById(id)
                .map(existing -> {
                    existing.setCourseName(updated.getCourseName());
                    return courseRepo.save(existing);
                })
                .orElseThrow(() -> new NotFoundException("Course not found: " + id));
    }

    @Transactional
    public void delete(UUID id) {
        if (!courseRepo.existsById(id))
            throw new NotFoundException("Course not found: " + id);
        courseRepo.deleteById(id);
    }
}
