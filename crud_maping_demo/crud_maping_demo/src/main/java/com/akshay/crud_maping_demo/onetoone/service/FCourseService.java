package com.akshay.crud_maping_demo.onetoone.service;

import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.onetoone.entity.Course;
import com.akshay.crud_maping_demo.onetoone.repository.CourseRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FCourseService {

    private final CourseRepo courseRepo;

    @Transactional
    public Course createCourse(Course course) {
        if (course.getCourseName() == null || course.getCourseName().isBlank())
            throw new BadRequestException("Course name cannot be empty");
        return courseRepo.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(UUID id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
    }

    public Course getCourseByIdJPQL(UUID id) {
        Course c = courseRepo.fetchByIdJPQL(id);
        if (c == null) throw new NotFoundException("Course not found with id: " + id);
        return c;
    }

    public Course getCourseByName(String name) {
        Course c = courseRepo.findByCourseName(name);
        if (c == null) throw new NotFoundException("Course not found with name: " + name);
        return c;
    }

    public List<Course> searchCourseByNamePart(String part) {
        List<Course> list = courseRepo.findByCourseNameContaining(part);
        if (list.isEmpty()) throw new NotFoundException("No courses found containing: " + part);
        return list;
    }

    public Course getCourseByStudentId(UUID studentId) {
        Course c = courseRepo.findByStudent_Id(studentId);
        if (c == null) throw new NotFoundException("Course not found for studentId: " + studentId);
        return c;
    }

    public Course getCourseByStudentName(String name) {
        Course c = courseRepo.findByStudent_NameIgnoreCase(name);
        if (c == null) throw new NotFoundException("Course not found for student name: " + name);
        return c;
    }

    public List<Object[]> countStudentsGroupByCourse() {
        return courseRepo.countStudentsGroupByCourse();
    }

    public List<Object[]> groupCourseByNameAndId() {
        return courseRepo.groupCourseByNameAndId();
    }

    @Transactional
    public Course updateCourse(UUID id, Course updated) {
        return courseRepo.findById(id)
                .map(existing -> {
                    existing.setCourseName(updated.getCourseName());
                    return courseRepo.save(existing);
                })
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
    }

    @Transactional
    public void deleteCourse(UUID id) {
        if (!courseRepo.existsById(id))
            throw new NotFoundException("Course not found with id: " + id);
        courseRepo.deleteById(id);
    }
}
