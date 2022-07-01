package com.learnersacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnersacademy.dao.CourseRepository;
import com.learnersacademy.model.Course;

@Service
public class CourseService {

	private CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	public Course getCourseById(Long courseId) {
		return courseRepository.findById(courseId).get();
	}
	
	public List <Course> getCoursesBySubjectId(Long subjectId){
		return courseRepository.findBySubjectId(subjectId);
	}

	public void deleteCourseById(Long courseId) {
		courseRepository.deleteById(courseId);
	}
}
