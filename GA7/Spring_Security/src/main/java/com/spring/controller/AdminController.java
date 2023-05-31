package com.spring.controller;

import com.spring.entities.Student;
import com.spring.entities.Subject;
import com.spring.request.AssignSubReq;
import com.spring.request.FindStudent;
import com.spring.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/student")
public class AdminController {
    @Autowired
    private StudentServiceI studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student user1 = this.studentService.addStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @PutMapping("/updateStudent/{email}")
    public ResponseEntity<Student> updateStudent(@PathVariable String email, @RequestBody Student student) {
        Student student1 = this.studentService.updateStudent(email, student);
        if (student1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(student1);
    }

    @DeleteMapping("/deleteStudent/{email}")
    public void deleteStudent(@PathVariable String email) {
        this.studentService.deleteStudent(email);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = new ArrayList<>();
        list = this.studentService.getAllStudents();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @PostMapping("/getAllStudentsByCourseAndSemester")
    public ResponseEntity<List<Student>> getAllStudentsByCourseAndSemester(@RequestBody FindStudent findStudent) {
        List<Student> listOfStudent = this.studentService.getAllStudentsByCourseAndYearAndBranchAndSemester(findStudent);
        return ResponseEntity.status(HttpStatus.OK).body(listOfStudent);
    }

    @GetMapping("/findStudent/{email}")
    public ResponseEntity<Student> findStudentByEmail(@PathVariable String email){
        Student student=this.studentService.findByEmail(email);
        if(student==null)return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(student));
    }
    @PutMapping("/assignSubject")
    public ResponseEntity<Student> assignSubjectRequest(@RequestBody AssignSubReq assignSubReq){
        Student student = studentService.assignSubject(assignSubReq.getEmail(), assignSubReq.getSubjectCode());
        if(student==null)return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(student));
    }

    @GetMapping("/getAllSubjects/{email}")
    public ResponseEntity<Set<Subject>> getAllSubjectsByEmail(@PathVariable String email){
        Set<Subject> allSubjects = studentService.getAllSubjects(email);
        if(allSubjects==null)return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(allSubjects);
    }

    @DeleteMapping("/delAssignSubject")
    public void deleteAssignSubjects(@RequestBody AssignSubReq assignSubReq){
        this.studentService.deleteAssignSubject(assignSubReq.getEmail(),assignSubReq.getSubjectCode());

    }



}
