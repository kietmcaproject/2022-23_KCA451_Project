package com.spring.service;

import com.spring.entities.Subject;
import com.spring.request.AssignmentMarks;
import com.spring.request.ExamMarks;

import java.util.List;

public interface SubjectServiceI {
    List<Subject> getAllSubjects();

    Subject updateSubject(String subjectCode, Subject subject);

    void deleteSubject(long id);


    Subject addSubject(Subject subject);

    Subject findSubject(String subjectCode);

    Subject updateExams(long id, ExamMarks examMarks);

    Subject updateAssignments(long id, AssignmentMarks assignmentMarks);

    List<Subject> getAllExamMarks();

    List<Subject> getAllAssignmentMarks();
}
