package com.akshay.crud_maping_demo.onetoone.service;

import com.akshay.crud_maping_demo.exception.NotFoundException;
import com.akshay.crud_maping_demo.exception.BadRequestException;
import com.akshay.crud_maping_demo.onetoone.entity.Student;
import com.akshay.crud_maping_demo.onetoone.repository.StudentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FStudentService {

    private final StudentRepo studentRepo;

    @Transactional
    public Student createStudent(Student student) {
        if (student.getName() == null || student.getName().isBlank())
            throw new BadRequestException("Student name cannot be empty");
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudent(UUID id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
    }

    public Student getStudentJPQL(UUID id) {
        Student s = studentRepo.fetchByIdJPQL(id);
        if (s == null) throw new NotFoundException("Student not found with id: " + id);
        return s;
    }

    public Student getStudentByCourseId(UUID courseId) {
        Student s = studentRepo.findByCourse_Id(courseId);
        if (s == null) throw new NotFoundException("Student not found for courseId: " + courseId);
        return s;
    }

    public List<Student> searchByNameIgnoreCase(String name) {
        List<Student> list = studentRepo.findByNameIgnoreCase(name);
        if (list.isEmpty()) throw new NotFoundException("No students found with name: " + name);
        return list;
    }

    public List<Student> searchByNamePart(String part) {
        List<Student> list = studentRepo.findByNameContaining(part);
        if (list.isEmpty()) throw new NotFoundException("No students found containing: " + part);
        return list;
    }

    public List<Student> getStudentsByCourseName(String courseName) {
        List<Student> list = studentRepo.findByCourse_CourseName(courseName);
        if (list.isEmpty()) throw new NotFoundException("No students found for course name: " + courseName);
        return list;
    }

    public List<Object[]> groupStudentCountByCourse() {
        return studentRepo.groupStudentCountByCourse();
    }

    public List<Object[]> groupStudentByNameAndCourse() {
        return studentRepo.groupStudentByNameAndCourse();
    }

    @Transactional
    public Student updateStudent(UUID id, Student updated) {
        return studentRepo.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setCourse(updated.getCourse());
                    return studentRepo.save(existing);
                })
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
    }

    @Transactional
    public void deleteStudent(UUID id) {
        if (!studentRepo.existsById(id))
            throw new NotFoundException("Student not found with id: " + id);
        studentRepo.deleteById(id);
    }
}
