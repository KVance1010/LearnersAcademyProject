package com.learnersacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.learnersacademy.dao.StudentRepository;
import com.learnersacademy.model.Student;

@Controller
public class StudentListController {

	@Autowired
	StudentRepository studentRepository;

	// mapping HTTP GET requests
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentRepository.findAll());
		return "/students/students";
	}

	@GetMapping("/students/new")
	public String createStudentForm(Model model) { // model is an interface that allows you to link objects with attributes
		// create student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "/students/create-student";
	}

	// mapping HTTP POST requests
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentRepository.save(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{studentId}")
	public String editStudentForm(@PathVariable Long studentId, Model model) {
		model.addAttribute("student", studentRepository.findById(studentId).get());
		return "/students/edit-student";    // added students for the file path. html file is in a folder
	} 

	@PostMapping("/students/{studentId}")
	public String updateStudent(@PathVariable Long studentId, @ModelAttribute("student") Student student, Model model) {
		// get student from database by id
		Student existingStudent = studentRepository.findById(studentId).get();
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setPhoneNumber(student.getPhoneNumber());
		existingStudent.setEmail(student.getEmail());
		// save updated student object
		studentRepository.save(existingStudent);
		return "redirect:/students";
	}

	// handler method to handle delete student request
	@GetMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable Long studentId) {
		studentRepository.deleteById(studentId);
		return "redirect:/students";
	}

}
