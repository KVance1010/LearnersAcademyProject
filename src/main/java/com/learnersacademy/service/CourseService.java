package com.learnersacademy.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnersacademy.dao.CourseRepository;
import com.learnersacademy.model.Course;
import com.learnersacademy.model.Teacher;

@Service
public class CourseService {
	
	@Autowired 
	CourseRepository courseRepository;
		
	public HashMap <Course, String> findTeachersCourse(Long subjectId) {
		
	HashMap<Course, String> teacherCourse = new HashMap<>();
	List<Course> courses = courseRepository.findBySubjectId(subjectId);	
	
	courses.forEach((course) -> {
		Teacher teacher = courseRepository.findTeacherByCourseId(course.getCourseId());
		String foundTeacher = "No Current Teacher";
		
	    if (teacher != null) { 
	    	foundTeacher = teacher.getFirstName() + " " + teacher.getLastName();}
	    else{    	
	    foundTeacher = "No Current Teacher"; 
	    }
	    	
		teacherCourse.put(course, foundTeacher);
	});
	
	return teacherCourse;
	}

}
