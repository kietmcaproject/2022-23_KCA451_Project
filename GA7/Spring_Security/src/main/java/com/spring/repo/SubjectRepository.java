package com.spring.repo;

import com.spring.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Subject,String> {
    Subject findBySubjectCode(String subjectCode);

    Subject findById(long id);

    @Query("SELECT subject FROM Subject subject WHERE subject.examType is not null" )
    List<Subject> getAllExamMarks();

    @Query("SELECT subject FROM Subject subject WHERE subject.assignType is not null" )
    List<Subject> getAllAssignmentMarks();

    @Query("select subject from Subject subject group by subject.subjectCode")
    List<Subject> getUniqueSubject();



}
