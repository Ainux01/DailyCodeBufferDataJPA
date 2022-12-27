package com.example.dailycodebuffer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Teacher {
    @SequenceGenerator(name = "teacher_sequence",sequenceName = "teacher_sequence",allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "teacher_sequence")
    private Long teacherId;
    private String firstname;
    private String lastname;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "teacher",
            fetch = FetchType.EAGER
    )
    private List<Course> courses;
}
