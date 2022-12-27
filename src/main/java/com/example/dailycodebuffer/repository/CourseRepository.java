package com.example.dailycodebuffer.repository;

import com.example.dailycodebuffer.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAllByTitleContaining(String title, Pageable pageRequest);

    @Query(
            value = "select * from course c where c.teacher_id = :id ",
            nativeQuery = true
    )
    List<Course> getAllByTeacherId(@Param("id")Long id);
}
