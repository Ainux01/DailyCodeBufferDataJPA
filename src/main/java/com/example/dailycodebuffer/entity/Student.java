package com.example.dailycodebuffer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// L'annotation @Entity nous indique que cette classe est une classe persistante
// L'annotation @Table permet de fixer le nom de la table dans laquelle les instances de cette classe vont être écrites
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // to add the builder pattern
@Table(name = "tbl_student")
//uniqueConstraints = @UniqueConstraint(name = "email-unique",columnNames = "email_address")
public class Student {
    @Id
    @SequenceGenerator(name = "sequence-student",sequenceName = "sequence-student",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence-student")
    private Long StudentId;
    private String firstname;
    private String lastname;
    @Column(name = "email_address")
    private String emailId;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "name", column = @Column(name = "guardian_name")),
            @AttributeOverride(name = "email", column = @Column(name = "guardian_email")),
            @AttributeOverride(name = "mobile", column = @Column(name = "guardian_mobile"))
    })
    private Guardian guardian;

}
//However, it seems like the guardian person should be abstracted out to a separate class.
//
//The problem is that we don't want to create a separate table for those details.
//
//So, let's see what we can do: Embeddable and Embedded and AttributeOverrides annotation.
