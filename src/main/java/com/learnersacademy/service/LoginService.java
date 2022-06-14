package com.learnersacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnersacademy.dao.StudentRepository;
import com.learnersacademy.dao.TeacherRepository;
import com.learnersacademy.model.Student;
import com.learnersacademy.model.Teacher;

@Service
public class LoginService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	public Student authenticationStudent(Long id, String password) {
		return studentRepository.findByStudentIdAndPassword(id, password).orElse(null);
	}
	public Teacher authenticationTeacher(Long id, String password) {
		return teacherRepository.findByTeacherIdAndPassword(id, password).orElse(null);
	}
	
}
