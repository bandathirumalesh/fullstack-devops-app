package com.example.simpleapp.service;

import com.example.simpleapp.dto.StudentForm;
import com.example.simpleapp.model.Student;
import com.example.simpleapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll().stream()
                .sorted((first, second) -> second.getCreatedAt().compareTo(first.getCreatedAt()))
                .toList();
    }

    public boolean rollNumberExists(String rollNumber) {
        return studentRepository.existsByRollNumberIgnoreCase(rollNumber);
    }

    public Student registerStudent(StudentForm form) {
        Student student = new Student();
        student.setFullName(form.getFullName());
        student.setRollNumber(form.getRollNumber());
        student.setEmail(form.getEmail());
        student.setDepartment(form.getDepartment());
        student.setYearOfStudy(form.getYearOfStudy());
        student.setPhoneNumber(form.getPhoneNumber());
        student.setAddress(form.getAddress());
        return studentRepository.save(student);
    }
}
