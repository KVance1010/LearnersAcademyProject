package com.learnersacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.learnersacademy.dao.CourseRepository;
import com.learnersacademy.dao.SubjectRepository;
import com.learnersacademy.model.Course;
import com.learnersacademy.model.Subject;

@Controller
public class CourseListController {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	SubjectRepository subjectRepository;

	// mapping HTTP GET requests
	@GetMapping("/courses-subjects")
	public String listCoursesAndSubjects(Model model) {
		
		List<Course> course = courseRepository.findAll();
		
		if (course != null) { model.addAttribute("condition2", Boolean.TRUE);}
		else { model.addAttribute("condition2", Boolean.FALSE);}
		
		List<Subject> subject = subjectRepository.findAll();
		
		if (subject != null) { model.addAttribute("condition1", Boolean.TRUE);}
		else { model.addAttribute("condition1", Boolean.FALSE);}
		
		model.addAttribute("subject", subject);
		model.addAttribute("course", course);
		return "/courseSubject/course-subject-list";
	}

	// handler method to handle delete student request
		@GetMapping("/subject/{subjectId}")
		public String deleteSubject(@PathVariable Long subjectId) {
			subjectRepository.deleteById(subjectId);
			return "redirect:/courses-subjects";
		}
		
		@GetMapping("/new-course")
		public String createCourse(Model model) {          
			Course course = new Course();
			List<Subject> subject = subjectRepository.findAll();
			model.addAttribute("course", course);              
			model.addAttribute("subject", subject);       
			return "/courseSubject/create-course"; 
		}
		
		@PostMapping("/course/save")
		public String createCourse(Course course, Model model){
			courseRepository.save(course);
			return "redirect:/courses-subjects";
		}
		
		
}
