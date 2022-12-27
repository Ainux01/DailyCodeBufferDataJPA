package com.example.dailycodebuffer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseMaterialId;
    private String url;

    //As we define FETCH TYPE as Lazy, and we exclude course from toString method,
    // when we fetch courseMaterial from db, we only get courseMaterial without course.
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
            //When we specify that optional is false : we aren't able to save courseMaterial that doesn't have course
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
