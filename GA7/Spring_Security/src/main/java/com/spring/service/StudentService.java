package com.spring.service;

import com.spring.entities.Student;
import com.spring.entities.Subject;
import com.spring.repo.StudentRepository;
import com.spring.repo.SubjectRepository;
import com.spring.request.FindStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService implements StudentServiceI {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public Student updateStudent(String email, Student student) {
        Student student1 = this.studentRepository.findByEmail(email);
        student1.setAddress(student.getAddress());
        student1.setBranch(student.getBranch());
        student1.setCourse(student.getCourse());
        student1.setAlternateEmail(student.getAlternateEmail());
        student1.setEmail(student.getEmail());
        student1.setFatherName(student.getFatherName());
        student1.setMotherName(student.getMotherName());
        student1.setDob(student.getDob());
        student1.setFirstName(student.getFirstName());
        student1.setPhone(student.getPhone());
        student1.setSection(student.getSection());
        student1.setRole(student.getRole());
        student1.setRollNum(student.getRollNum());
        student1.setPassword(student.getPassword());
        student1.setMentor(student.getMentor());
        student1.setYear(student.getYear());
        student1.setLastName(student.getLastName());
        this.studentRepository.save(student1);
        return student1;
    }

    @Override
    public void deleteStudent(String email) {
        Student student = this.studentRepository.findByEmail(email);
        this.studentRepository.delete(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> getAllStudentsByCourseAndYearAndBranchAndSemester(FindStudent findStudent) {
        return this.studentRepository.findAllByCourseAndYearAndSemesterAndBranch(findStudent.getCourse(), findStudent.getYear(),
                findStudent.getSemester(), findStudent.getBranch());
    }

    @Override
    public Student findByEmail(String email) {
        Student student = this.studentRepository.findByEmail(email);
        return student;
    }

    @Override
    public Student assignSubject(String email, String subjectCode) {

        Student student = this.studentRepository.findByEmail(email);
        Subject subject = this.subjectRepository.findBySubjectCode(subjectCode);
        if (student == null || subject == null) return null;
        student.getSubjects().add(subject);
        studentRepository.save(student);
        return student;

    }

    @Override
    public Set<Subject> getAllSubjects(String email) {
        Student student = this.studentRepository.findByEmail(email);
        return student.getSubjects();
    }

    @Override
    public void deleteAssignSubject(String email, String subjectCode) {
        Student student = this.studentRepository.findByEmail(email);
        Subject subject = subjectRepository.findBySubjectCode(subjectCode);
        student.getSubjects().remove(subject);
        this.studentRepository.save(student);
    }


   
}
