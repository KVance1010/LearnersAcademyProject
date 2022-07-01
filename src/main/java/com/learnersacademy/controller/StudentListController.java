package com.learnersacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.learnersacademy.model.Course;
import com.learnersacademy.model.Student;
import com.learnersacademy.service.CourseService;
import com.learnersacademy.service.StudentService;

@Controller
public class StudentListController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	// Main student page
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "/students/students";
	}

	// Create a new Student
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student = new Student();
		List<Course> course = courseService.getAllCourses();
		model.addAttribute("student", student);
		model.addAttribute("course", course);
		return "/students/create-student";
	}

	// Save method for new Student
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	// Editing a Students information
	@GetMapping("/students/edit/{studentId}")
	public String editStudentForm(@PathVariable Long studentId, Model model) {
		model.addAttribute("student", studentService.getStudentById(studentId));
		return "/students/edit-student";
	}

	// Adding Courses to the Student
	@GetMapping("/students-add-course/{studentId}")
	public String addCourseToStudent(@PathVariable Long studentId, Model model) {
		model.addAttribute("course", courseService.getAllCourses());
		model.addAttribute("student", studentService.getStudentById(studentId));
		return "/students/add-course-student";
	}

	// Saving the Edited students information
	@PostMapping("/students/{studentId}")
	public String updateStudent(@PathVariable Long studentId, @ModelAttribute("student") Student student, Model model) {
		Student existingStudent = studentService.getStudentById(studentId);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setPhoneNumber(student.getPhoneNumber());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPassword(student.getPassword());
		studentService.saveStudent(existingStudent);
		return "redirect:/students";
	}

	// Adding a course
	@GetMapping("/add-course/{studentId}/{courseId}")
	public String addCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
		Course course = courseService.getCourseById(courseId);
		Student student = studentService.getStudentById(studentId);
		student.addCourse(course);
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	// Deletes the Students information
	@GetMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable Long studentId) {
		studentService.deleteStudentById(studentId);
		return "redirect:/students";
	}

	// Deletes Course from Student
	@GetMapping("/student-delete-course/{studentId}/{courseId}")
	public String deleteStudentCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
		Course course = courseService.getCourseById(courseId);
		Student student = studentService.getStudentById(studentId);
		student.deleteCourse(course);
		studentService.saveStudent(student);
		return "redirect:/students";
	}

}
