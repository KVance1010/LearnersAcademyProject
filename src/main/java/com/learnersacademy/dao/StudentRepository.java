package com.learnersacademy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnersacademy.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	// Find Student by Id and Password
	Optional<Student> findByStudentIdAndPassword(Long studentId, String password);
}
