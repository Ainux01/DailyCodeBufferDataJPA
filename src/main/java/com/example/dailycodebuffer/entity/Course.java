package com.example.dailycodebuffer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"teacher","students"})
public class Course {
    @Id
    @SequenceGenerator(name="course-sequence",sequenceName = "course-sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course-sequence")
    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            mappedBy = "course",
            fetch = FetchType.EAGER

    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "courses-students",
            joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "StudentId")
    )
    private List<Student> students;

    public void addStudent(Student s){
        if (students == null) students = new ArrayList<>();
        students.add(s);
    }
}
