package com.spring.service;

import com.spring.entities.Subject;
import com.spring.repo.SubjectRepository;
import com.spring.request.AssignmentMarks;
import com.spring.request.ExamMarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements SubjectServiceI {

    @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public List<Subject> getAllSubjects() {

        return this.subjectRepository.getUniqueSubject();
    }

    @Override
    public Subject updateSubject(String subjectCode, Subject subject) {
        Subject subject1=this.subjectRepository.findBySubjectCode(subjectCode);
        subject1.setSubjectName(subject.getSubjectName());
        this.subjectRepository.save(subject1);
        return subject1;
    }

    @Override
    public void deleteSubject(long id) {

        Subject subject = this.subjectRepository.findById(id);
        this.subjectRepository.delete(subject);
    }


    @Override
    public Subject addSubject(Subject subject) {
        Subject s=this.subjectRepository.findBySubjectCode(subject.getSubjectCode());
            return this.subjectRepository.save(subject);
    }

    @Override
    public Subject findSubject(String subjectCode) {
        Subject subject=this.subjectRepository.findBySubjectCode(subjectCode);
        return subject;
    }


    @Override
    public Subject updateExams(long id, ExamMarks examMarks) {
        Subject subject=this.subjectRepository.findById( id);
        subject.setExamType(examMarks.getExamType());
        subject.setMaxExamMarks(examMarks.getMaxExamMarks());
        subject.setObtainedExamMarks(examMarks.getObtainedExamMarks());
        this.subjectRepository.save(subject);
        return subject;
    }

    @Override
    public Subject updateAssignments(long id, AssignmentMarks assignmentMarks) {
        Subject subject=this.subjectRepository.findById( id);
        subject.setObtainedAssignMarks(assignmentMarks.getObtainedAssignMarks());
        subject.setMaxAssignMarks(assignmentMarks.getMaxAssignMarks());
        subject.setAssignType(assignmentMarks.getAssignType());
        this.subjectRepository.save(subject);
        return subject;
    }

    @Override
    public List<Subject> getAllExamMarks() {
        List<Subject> list=this.subjectRepository.getAllExamMarks();
        return list;
    }

    @Override
    public List<Subject> getAllAssignmentMarks() {
        List<Subject> list=this.subjectRepository.getAllAssignmentMarks();
        return list;
    }



}
