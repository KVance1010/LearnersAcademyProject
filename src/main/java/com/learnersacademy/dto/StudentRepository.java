package com.learnersacademy.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnersacademy.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
