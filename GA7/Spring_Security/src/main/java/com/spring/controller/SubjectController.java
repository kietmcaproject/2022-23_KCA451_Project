package com.spring.controller;

import com.spring.entities.Subject;
import com.spring.request.AssignmentMarks;
import com.spring.request.ExamMarks;
import com.spring.service.SubjectServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "http://localhost:3000")
public class SubjectController {

    @Autowired
    private SubjectServiceI subjectService;


    @PostMapping("/addSubject")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {

        Subject user1 = this.subjectService.addSubject(subject);
        if (user1 == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @PutMapping("/updateSubject/{subjectCode}")
    public ResponseEntity<Subject> updateSubject(@PathVariable String subjectCode, @RequestBody Subject subject) {
        Subject subject1 = this.subjectService.updateSubject(subjectCode, subject);
        if (subject1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(subject1);
    }

    @DeleteMapping("/deleteSubject/{id}")
    public void deleteSubject(@PathVariable long id) {
        this.subjectService.deleteSubject(id);
    }

    @GetMapping("/getAllSubjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> list = new ArrayList<>();
        list = this.subjectService.getAllSubjects();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/findSubject/{subjectCode}")
    public ResponseEntity<Subject> findSubject(@PathVariable String subjectCode) {
        Subject subject = this.subjectService.findSubject(subjectCode);
        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(subject));
    }

    @PutMapping("/updateMarks/{id}")
    public ResponseEntity<Subject> updateMarks(@PathVariable long id, @RequestBody ExamMarks examMarks) {
        Subject subject1 = this.subjectService.updateExams(id, examMarks);
        if (subject1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(subject1);
    }


    @PutMapping("/updateAssignments/{id}")
    public ResponseEntity<Subject> updateAssignments(@PathVariable long id, @RequestBody AssignmentMarks assignmentMarks) {
        Subject subject1 = this.subjectService.updateAssignments(id, assignmentMarks);

        if (subject1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(subject1);
    }

    @GetMapping("/getAllExamMarks")
    public ResponseEntity<List<Subject>> getAllMarks() {
        List<Subject> list = this.subjectService.getAllExamMarks();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/getAllAssignMarks")
    public ResponseEntity<List<Subject>> getAllAssignments() {
        List<Subject> list = this.subjectService.getAllAssignmentMarks();
        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }


}
