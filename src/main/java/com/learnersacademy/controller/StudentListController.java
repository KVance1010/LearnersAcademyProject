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
import com.learnersacademy.dao.StudentRepository;
import com.learnersacademy.model.Course;
import com.learnersacademy.model.Student;

@Controller
public class StudentListController {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CourseRepository courseRepository;

	// Main student page
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentRepository.findAll());
		return "/students/students";
	}
    // Create a new Student
	@GetMapping("/students/new")
	public String createStudentForm(Model model) { 
		Student student = new Student();
		List<Course> course = courseRepository.findAll();
		model.addAttribute("student", student);
		model.addAttribute("course", course);
		return "/students/create-student";
	}

	// Save method for new Student
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentRepository.save(student);
		return "redirect:/students";
	}

	// Editing a Students information
	@GetMapping("/students/edit/{studentId}")
	public String editStudentForm(@PathVariable Long studentId, Model model) {
		model.addAttribute("student", studentRepository.findById(studentId).get());
		return "/students/edit-student";    
	} 
	
	// Adding Courses to the Student
		@GetMapping("/students-add-course/{studentId}")
		public String addCourseToStudent(@PathVariable Long studentId, Model model) {
			model.addAttribute("course", courseRepository.findAll());
			model.addAttribute("student", studentRepository.findById(studentId).get());
			return "/students/add-course-student";    
		} 

	// Saving the Edited students information
	@PostMapping("/students/{studentId}")
	public String updateStudent(@PathVariable Long studentId, @ModelAttribute("student") Student student, Model model) {
		Student existingStudent = studentRepository.findById(studentId).get();
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setPhoneNumber(student.getPhoneNumber());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPassword(student.getPassword());
		studentRepository.save(existingStudent);
		return "redirect:/students";
	}
	

	// Adding a course
	@GetMapping("/add-course/{studentId}/{courseId}")
	public String addCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
		Course course = courseRepository.findById(courseId).get();
		Student student = studentRepository.findById(studentId).get();
		student.addCourse(course);
		studentRepository.save(student);
		return "redirect:/students";
	}
	
	// Deletes the Students information
	@GetMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable Long studentId) {
		studentRepository.deleteById(studentId);
		return "redirect:/students";
	}
	
	// Deletes Course from Student
		@GetMapping("/student-delete-course/{studentId}/{courseId}")
		public String deleteStudentCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
			Course course = courseRepository.findById(courseId).get();
			Student student = studentRepository.findById(studentId).get();
			student.deleteCourse(course);
			studentRepository.save(student);
			return "redirect:/students";
		}

}
