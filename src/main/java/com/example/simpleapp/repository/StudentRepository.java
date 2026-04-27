package com.example.simpleapp.repository;

import com.example.simpleapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByRollNumberIgnoreCase(String rollNumber);
}
