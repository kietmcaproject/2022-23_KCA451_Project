package com.spring.entities;

import javax.persistence.*;

@Entity
@Table(name = "SUBJECT")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "SUB_CODE")
    private String subjectCode;
    @Column(name = "SUB_NAME")
    private String subjectName;

    @Column(name = "ASS_TYPE")
    private String assignType;

    @Column(name = "MAX_ASS_MARKS")
    private int maxAssignMarks;

    @Column(name = "OBT_ASSIGN_MARKS")
    private int obtainedAssignMarks;
    @Column(name = "MAX_EXAM_MARKS")
    private int maxExamMarks;

    @Column(name = "OBT_EXAM_MARKS")
    private int obtainedExamMarks;

    @Column(name = "EXAM_TYPE")
    private String examType;

    public String getAssignType() {
        return assignType;
    }

    public void setAssignType(String assignType) {
        this.assignType = assignType;
    }

    public int getMaxAssignMarks() {
        return maxAssignMarks;
    }

    public void setMaxAssignMarks(int maxAssignMarks) {
        this.maxAssignMarks = maxAssignMarks;
    }

    public int getObtainedAssignMarks() {
        return obtainedAssignMarks;
    }

    public void setObtainedAssignMarks(int obtainedAssignMarks) {
        this.obtainedAssignMarks = obtainedAssignMarks;
    }


    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public int getMaxExamMarks() {
        return maxExamMarks;
    }

    public void setMaxExamMarks(int maxExamMarks) {
        this.maxExamMarks = maxExamMarks;
    }

    public int getObtainedExamMarks() {
        return obtainedExamMarks;
    }

    public void setObtainedExamMarks(int obtainedExamMarks) {
        this.obtainedExamMarks = obtainedExamMarks;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }


    public String getSubjectName() {
        return subjectName;
    }


    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}






