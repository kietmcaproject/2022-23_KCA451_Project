package com.spring.service;

import com.spring.entities.Student;
import com.spring.entities.Subject;
import com.spring.request.FindStudent;

import java.util.List;
import java.util.Set;

public interface StudentServiceI {
    Student addStudent(Student student);

    Student updateStudent(String email, Student student);

    void deleteStudent(String email);

    List<Student> getAllStudents();


    List<Student> getAllStudentsByCourseAndYearAndBranchAndSemester(FindStudent findStudent);


    Student findByEmail(String email);

    Student assignSubject(String email, String subCode);

    Set<Subject> getAllSubjects(String email);

    void deleteAssignSubject(String email,String subjectCode);



}
