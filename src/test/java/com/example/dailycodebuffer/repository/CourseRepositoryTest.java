package com.example.dailycodebuffer.repository;

import com.example.dailycodebuffer.entity.Course;
import com.example.dailycodebuffer.entity.Student;
import com.example.dailycodebuffer.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository repository;

    @Test
    public void printCourses(){
        List<Course> courses =  repository.findAll();
        System.out.println(courses);
    }
    @Test
    public void saveCourseWithTeacher(){
        Teacher t = Teacher.builder()
                .firstname("Mohammed")
                .lastname("Anwar")
                .build();

        Course c = Course.builder()
                .title(".net")
                .credit(8)
                .teacher(t)
                .build();

        repository.save(c);
    }

    @Test
    public void printAllPaginations(){
        Pageable firstPageableWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1,2);

        List<Course> courses1 = repository.findAll(firstPageableWithThreeRecords).getContent();
        System.out.println(courses1);

        var totalElements = repository.findAll(firstPageableWithThreeRecords)
                .getTotalElements();//5

        long totalPages = repository.findAll(firstPageableWithThreeRecords)
                .getTotalPages();//2

        var courses2 = repository.findAll(secondPageWithTwoRecords).getContent();
        System.out.println("total elements : "+totalElements);
        System.out.println("total Pages : "+totalPages);

        System.out.println(courses2);
    }

    @Test
    public void printAllSortings(){
        Pageable sortedByTitle = PageRequest.of(
                0,3, Sort.by("title")
        );
        List<Course> courses1 = repository.findAll(sortedByTitle).getContent();
        System.out.println(courses1);

        Pageable sortedByCreditDescending = PageRequest.of(0,3,Sort.by("credit").descending());
        List<Course> courses2 = repository.findAll(sortedByCreditDescending).getContent();
        System.out.println(courses2);
    }

    @Test
    public void printfindByTitleContaining(){
        Pageable firstPageWithTenRecords = PageRequest.of(0,10);
        Page<Course> p = repository.findAllByTitleContaining("a",firstPageWithTenRecords);
        System.out.println(p.getContent());
        System.out.println(p);
    }

    @Test
    public void printgetAllByTeacherId(){
        List<Course> courses = repository.getAllByTeacherId(1L);
        System.out.println("Number of courses : "+courses.size());
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudents(){
        Teacher t = Teacher.builder()
                .firstname("Asmaa")
                .lastname("Retbi")
                .build();

        Student st = Student.builder()
                .firstname("Amine")
                .lastname("Arrouk")
                .emailId("amimed2001@gmail.com")
                .build();

        Course c = Course.builder()
                .teacher(t)
                .credit(30)
                .title("STRAPI")
                .build();
        c.addStudent(st);

        repository.save(c);
    }

}