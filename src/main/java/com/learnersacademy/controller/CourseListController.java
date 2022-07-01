package com.learnersacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnersacademy.dto.TeacherCourse;
import com.learnersacademy.model.Course;
import com.learnersacademy.model.Subject;
import com.learnersacademy.service.CourseService;
import com.learnersacademy.service.SubjectService;

@Controller
public class CourseListController{

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;

//	@Autowired
//	private TeacherService teacherService;

	// Main list of courses and subjects
	@GetMapping("/courses-subjects")
	public String listCoursesAndSubjects(Model model) {
		List<Course> course = courseService.getAllCourses();
		List<Subject> subject = subjectService.getAllSubjects();
		model.addAttribute("subject", subject);
		model.addAttribute("course", course);
		return "/courseSubject/course-subject-list";
	}

	// Delete a Subject
	@GetMapping("/subject-delete/{subjectId}")
	public String deleteSubject(@PathVariable Long subjectId) {
		subjectService.deleteSubjectById(subjectId);
		return "redirect:/courses-subjects";
	}

	// Delete a Course
	@GetMapping("/course-delete/{courseId}")
	public String deleteCourse(@PathVariable Long courseId) {
		courseService.deleteCourseById(courseId);
		return "redirect:/courses-subjects";
	}

	// Create a Subject
	@GetMapping("/subject-save")
	public String addSubject(@RequestParam String subjectName) {
		Subject subject = new Subject();
		subject.setSubjectName(subjectName);
		subjectService.saveSubject(subject);
		return "redirect:/courses-subjects";
	}

	// Create a course
	@GetMapping("/new-course")
	public String createCourse(Model model) {
		Course course = new Course();
		List<Subject> subject = subjectService.getAllSubjects();
		model.addAttribute("course", course);
		model.addAttribute("subject", subject);
		return "/courseSubject/create-course";
	}

	// Save the course
	@PostMapping("/course/save")
	public String createCourse(Course course, Model model) {
		courseService.saveCourse(course);
		return "redirect:/courses-subjects";
	}

//	                  Took this out for now will need to fix HTML to show teachers not courses
//	// Add a teacher to a subject and course
//	@GetMapping("/add-teacher/subject/{subjectId}/{courseId}")
//	public String AddTeacherToSubject(@PathVariable Long subjectId, @PathVariable Long courseId,
//			@ModelAttribute("subject") Subject subject, Model model) {
//		Subject currentSubject = subjectService.findById(subjectId).get();
//		Course course = courseService.findById(courseId).get();
//		List<Teacher> teacher = teacherService.findTeacherBySubjectId(subjectId);
//		model.addAttribute("subject", currentSubject);
//		model.addAttribute("teacher", teacher);
//		model.addAttribute("course", course);
//		return "/courseSubject/add-teacher-to-subject";
//	}

	// Save the teacher to a course and subject
	@PostMapping("/suject-teacher/save")
	public String addteacher(@ModelAttribute("subject") Subject subject, Model model) {
		subjectService.saveSubject(subject);
		return "redirect:/courses-subjects";
	}

	// Editing form for Course
	@GetMapping("/course-edit/{courseId}")
	public String editCourseForm(@PathVariable Long courseId, Model model) {
		model.addAttribute("course", courseService.getCourseById(courseId));
		return "/courseSubject/edit-course";    
	} 
	// Update the course
	@PostMapping("/course-update/{courseId}")
	public String updateCourse(@PathVariable Long courseId, @ModelAttribute("course") Course course, Model model) {
		Course existingCourse = courseService.getCourseById(courseId);
		existingCourse.setCourseName(course.getCourseName());
		existingCourse.setDescription(course.getDescription());
		courseService.saveCourse(existingCourse);
		return "redirect:/courses-subjects";
	}

	// Show a list of Courses and Teachers by Subject
	@GetMapping("/subject-show/{subjectId}")
	public String showCourseBySubject(@PathVariable Long subjectId, @ModelAttribute("subject") Subject subject,
			Model model) {
		Subject existingSubject = subjectService.getSubjectById(subjectId);
		List <TeacherCourse> teacherAndCourse = subjectService.getTeacherAndCourseBySubject(subjectId);
		model.addAttribute("teacherCourse", teacherAndCourse);
		model.addAttribute("subject", existingSubject);
		return "/courseSubject/show-subject-courses";
	}
}
