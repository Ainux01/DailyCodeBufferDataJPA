package com.example.dailycodebuffer.repository;

import com.example.dailycodebuffer.entity.Guardian;
import com.example.dailycodebuffer.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest // if i want to avoid impacting my database
class StudentRepositoryTest {
   @Autowired
   private StudentRepository studentRepository;

   @Test
    public void saveStudent(){
       Student s = Student.builder()
               .firstname("Ahmed")
               .lastname("Swamer")
               .emailId("amimed2002@gmail.com")
//               .guardianEmail("Hamza@gmail.com")
//               .guardianName("Hamza")
//               .guardianMobile("0768987654")
               .build();
       studentRepository.save(s);
   }

   @Test
   public void saveStudentWithGuardian(){
      Guardian g = Guardian.builder()
              .name("hamza")
              .email("arrouk@gmail.com")
              .mobile("0678384143")
              .build();

      Student s = Student.builder()
              .firstname("Ahmed")
              .lastname("Swamer")
              .emailId("amimed2003@gmail.com")
              .guardian(g)
              .build();

      studentRepository.save(s);
   }

   @Test
    public void printAllStudents(){
       List<Student> students = studentRepository.findAll();
       students.stream()
               .forEach(System.out::println);
   }

   @Test
   public void printStudentsByFirstName(){
      List<Student> students = studentRepository.findByFirstname("Ahmed");
      students.stream().forEach(System.out::println);
   }

   @Test
   public void printStudentByFirstNameAndLastname(){
      List<Student> students = studentRepository.findByFirstnameAndLastname("Ahmed","Swamer");
      System.out.println(students) ;
   }

   @Test
   public void printStudentByEmailAddress(){
      Student s = studentRepository.getStudentByEmailAddress("amimed2001@gmail.com");
      System.out.println(s);
   }

   @Test
   public void printStudentFirstNameByEmailAddress(){
      String name = studentRepository.getStudentFisrtnameByEmailAddress("amimed2002@gmail.com");
      System.out.println(name);
   }

   @Test
   public void printStudentByEmailAddressNative(){
      Student s = studentRepository.getStudentByEmailAddressNative("amimed2001@gmail.com");
      System.out.println(s);
   }

   @Test
   public void printgetStudentGuardianNameByEmailAddressNative(){
      String guardian_name = studentRepository.getStudentGuardianNameByEmailAddressNative("amimed2003@gmail.com");
      System.out.println(guardian_name);
      assertEquals("hamza",guardian_name);
   }
   @Test
   public void printgetStudentByEmailAddressNativeNamedParams(){
      Student s = studentRepository.getStudentByEmailAddressNativeNamedParams("amimed2003@gmail.com");
      System.out.println(s);
   }
   @Test
   void printupdateStudentFirstnameByEmail(){
      int n = studentRepository.updateStudentFirstnameByEmail("Yassine","amimed2001@gmail.com");
      System.out.println(n);
   }

}