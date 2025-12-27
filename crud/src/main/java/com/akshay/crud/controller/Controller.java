package com.akshay.crud.controller;

import com.akshay.crud.entity.Course;
import com.akshay.crud.entity.Student;
import com.akshay.crud.reposetory.CourseRepo;
import com.akshay.crud.reposetory.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class Controller {

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    StudentRepository studentRepository;


    @RequestMapping("/")

    public boolean doCrud() {
        try {
            log.info("CRUD started");

            Course course1 = new Course("Mathematical");
            Course course2 = new Course("Science");

            courseRepo.save(course1);
            courseRepo.save(course2);

            Student student = Student.builder()
                    .studentName("Akshay")
                    .DOB(LocalDate.of(2004, 9, 3))
                    .course(course1)
                    .build();

            studentRepository.save(student);
            course1.setStudent(student);

            log.info("Inserted courses:");
            log.info(course1.toString());
            log.info(course2.toString());

            log.info("Inserted student:");
            log.info(student.toString());

            List<Course> courseList = (List<Course>) courseRepo.findAll();
            log.info("All courses after insert:");
            for (Course c : courseList) {
                log.info(c.toString());
            }

            List<Student> studentList = (List<Student>) studentRepository.findAll();
            log.info("All students after insert:");
            for (Student s : studentList) {
                log.info(s.toString());
            }

            Student s1 = studentRepository.findById(student.getId()).orElseThrow();
            s1.setStudentName("Akshay Updated");
            studentRepository.save(s1);
            log.info("Updated student:");
            log.info(s1.toString());

            courseRepo.delete(course2);
            log.info("Deleted course:");
            log.info(course2.toString());

            log.info("CRUD finished");
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }
}