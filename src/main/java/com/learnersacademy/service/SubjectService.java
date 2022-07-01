package com.learnersacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnersacademy.dao.SubjectRepository;
import com.learnersacademy.dto.TeacherCourse;
import com.learnersacademy.model.Subject;	


@Service
public class SubjectService {

	private SubjectRepository subjectRepository;

	public SubjectService(SubjectRepository subjectRepository) {
		super();
		this.subjectRepository = subjectRepository;
	}

	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}
	
	public List<TeacherCourse> getTeacherAndCourseBySubject(Long subjectId){
		return subjectRepository.findTeacherAndCourseBySubject(subjectId);
	}
	
	public List<Subject> getSubjectByTeacherID(Long teacherId){
		return subjectRepository.findSubjectsByTeacherId(teacherId);
	}

	public Subject saveSubject(Subject subject) {
		return subjectRepository.save(subject);
	}

	public Subject getSubjectById(Long subjectId) {
		return subjectRepository.findById(subjectId).get();
	}
	
	public void deleteSubjectById(Long subjectId) {
		subjectRepository.deleteById(subjectId);
	}
}
