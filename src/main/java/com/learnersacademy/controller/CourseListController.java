package com.learnersacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learnersacademy.dao.CourseRepository;
import com.learnersacademy.dao.SubjectRepository;

@Controller
public class CourseListController {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	SubjectRepository subjectRepository;

	// mapping HTTP GET requests
	@GetMapping("/courses-subjects")
	public String listCoursesAndSubjects(Model model1, Model model2) {
		model1.addAttribute("subjects", subjectRepository.findAll());
		model2.addAttribute("courses", courseRepository.findAll());
		return "/courseSubject/course-subject-list";
	}


	
}
