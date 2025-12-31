package com.akshay.crud_maping_demo.onetomany.service;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.onetomany.entity.SCourse;
import com.akshay.crud_maping_demo.onetomany.repository.SCourseRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SCourseService {

    private final SCourseRepo courseRepo;

    @Transactional
    public SCourse create(SCourse course) {
        if (course.getCourseName() == null || course.getCourseName().isBlank())
            throw new BadRequestException("Course name cannot be empty");
        return courseRepo.save(course);
    }

    public List<SCourse> getAll() {
        return courseRepo.findAll();
    }

    public SCourse getById(UUID id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
    }

    public SCourse getByIdJPQL(UUID id) {
        SCourse c = courseRepo.fetchByIdJPQL(id);
        if (c == null) throw new NotFoundException("Course not found with id: " + id);
        return c;
    }

    public List<SCourse> findByCourseName(String name) {
        List<SCourse> list = courseRepo.findByCourseName(name);
        if (list.isEmpty()) throw new NotFoundException("Course not found: " + name);
        return list;
    }

    public SCourse getByStudentId(UUID studentId) {
        SCourse c = courseRepo.findByStudents_Id(studentId);
        if (c == null) throw new NotFoundException("Course not found for student id: " + studentId);
        return c;
    }

    public SCourse getByStudentName(String name) {
        SCourse c = courseRepo.findByStudents_NameIgnoreCase(name);
        if (c == null) throw new NotFoundException("Course not found for student: " + name);
        return c;
    }

    public List<Object[]> countByCourseGroup() {
        return courseRepo.countStudentsGroupByCourse();
    }

    public List<Object[]> groupByNameAndId() {
        return courseRepo.groupCourseByNameAndId();
    }

    @Transactional
    public SCourse update(UUID id, SCourse updated) {
        return courseRepo.findById(id)
                .map(existing -> {
                    existing.setCourseName(updated.getCourseName());
                    return courseRepo.save(existing);
                })
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
    }

    @Transactional
    public void delete(UUID id) {
        if (!courseRepo.existsById(id))
            throw new NotFoundException("Course not found: " + id);
        courseRepo.deleteById(id);
    }
}
