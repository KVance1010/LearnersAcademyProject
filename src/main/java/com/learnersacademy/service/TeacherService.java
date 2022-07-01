package com.learnersacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnersacademy.dao.TeacherRepository;
import com.learnersacademy.model.Teacher;	


@Service
public class TeacherService {

	private TeacherRepository teacherRepository;

	public TeacherService(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	public Teacher saveTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public Teacher getTeacherById(Long teacherId) {
		return teacherRepository.findById(teacherId).get();
	}

	public void deleteTeacherById(Long teacherId) {
		teacherRepository.deleteById(teacherId);
	}
}
