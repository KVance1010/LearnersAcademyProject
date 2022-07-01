package com.learnersacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnersacademy.dao.StudentRepository;
import com.learnersacademy.model.Student;	


@Service
public class StudentService {

	private StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public Student getStudentById(Long studentId) {
		return studentRepository.findById(studentId).get();
	}

	public void deleteStudentById(Long studentId) {
		studentRepository.deleteById(studentId);
	}
}
