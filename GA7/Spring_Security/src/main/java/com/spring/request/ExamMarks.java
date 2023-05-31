package com.spring.request;

public class ExamMarks {

    private String examType;
    private int obtainedExamMarks;
    private int maxExamMarks;

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }


    public int getMaxExamMarks() {
        return maxExamMarks;
    }

    public int getObtainedExamMarks() {
        return obtainedExamMarks;
    }

    public void setObtainedExamMarks(int obtainedExamMarks) {
        this.obtainedExamMarks = obtainedExamMarks;
    }

    public void setMaxExamMarks(int maxExamMarks) {
        this.maxExamMarks = maxExamMarks;
    }
}
