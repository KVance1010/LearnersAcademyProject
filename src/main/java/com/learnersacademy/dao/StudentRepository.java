package com.learnersacademy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnersacademy.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	// Find Student by Id and Password
	//@Query(nativeQuery = true, value = "SELECT * FROM students AS s WHERE s.student_id = :studentId AND s.password = :password")
	Optional<Student> findByStudentIdAndPassword(Long studentId, String password);
}
