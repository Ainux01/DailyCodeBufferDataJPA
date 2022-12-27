package com.example.dailycodebuffer.repository;

import com.example.dailycodebuffer.entity.Course;
import com.example.dailycodebuffer.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository repository;

    @Test
    public void saveTeacher(){

        Course courseJava = Course.builder()
                .title("Java")
                .credit(14)
                .build();

        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(18)
                .build();

        Teacher t = Teacher.builder()
                .firstname("Issam")
                .lastname("Kabbaj")
//                .courses(List.of(courseJava,courseDBA))
                .build();

        repository.save(t);
    }

    @Test
    public void printTeachers(){
        List<Teacher> teachers = repository.findAll();
        System.out.println(teachers);
    }
}