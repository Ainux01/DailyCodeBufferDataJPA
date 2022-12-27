package com.example.dailycodebuffer.repository;

import com.example.dailycodebuffer.entity.Course;
import com.example.dailycodebuffer.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        Course c = Course.builder()
                .title("Daily")
                .credit(10)
                .build();

        CourseMaterial cm = CourseMaterial.builder()
                .url("www.dailycodebuffer.com")
                .course(c)
                .build();

        repository.save(cm);

        //First of All : This method will fail , because
        // to save coursematerial we have to save course, however course is not saved.That's why we should use cascade.
    }

    @Test
    public void findAllCourseMaterials(){
        List<CourseMaterial> courses = repository.findAll();
        System.out.println(courses);
    }
}
