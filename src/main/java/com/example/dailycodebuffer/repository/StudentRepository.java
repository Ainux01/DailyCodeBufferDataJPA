package com.example.dailycodebuffer.repository;

import com.example.dailycodebuffer.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstname(String firstname);

    List<Student> findByFirstnameContaining(String name);

    List<Student> findByLastnameNotNull();

    List<Student> findByGuardianName(String name);

    List<Student> findByFirstnameAndLastname(String firstname,String lastname);

    //JPQL
    @Query("select s from Student s where s.emailId=?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.firstname from Student s where s.emailId=?1")
    String getStudentFisrtnameByEmailAddress(String email);

    //Native Queries
    @Query(
            value = "select * from tbl_student s  where s.email_address=?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String email);

    @Query(
            value = "select guardian_name from tbl_student s where s.email_address=?1",
            nativeQuery = true
    )
    String getStudentGuardianNameByEmailAddressNative(String email);

    //Query Named Params
    @Query(
            value = "select * from tbl_student s where s.email_address= :email",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParams(
           @Param("email") String email);


    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set firstname = :firstname where email_address = :email",
            nativeQuery = true
    )
    int updateStudentFirstnameByEmail(@Param("firstname") String firstname,@Param("email") String email);
    // this method returns the number of rows that are updated


    // todo : Summary
    //@Query annotation, it provides us with the opportunity to write a specific JPQL or
    // SQL query in the @Query annotation(for sql we use nativeQuery=true)

    //The @Modifying annotation is used to enhance the @Query annotation so that we can execute not only SELECT queries,
    // but also INSERT, UPDATE, DELETE, and even DDL queries.

    //Let's see what happens when we don't put the @Modifying annotation on the delete query:
    //When we execute the above method, we get an InvalidDataAccessApiUsage exception.



}
