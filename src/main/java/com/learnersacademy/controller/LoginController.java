package com.learnersacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnersacademy.dao.StudentRepository;
import com.learnersacademy.dao.TeacherRepository;
import com.learnersacademy.model.Student;
import com.learnersacademy.model.Teacher;
import com.learnersacademy.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	TeacherRepository teacherRepository;

	@GetMapping("/")
	public String loginPage(Model model) {
		model.addAttribute("loginRequest", new Student());
		return "/login/login-page";
	}

	@PostMapping("/login")
	public String authentacateUser(@RequestParam Long id, @RequestParam String password, @RequestParam String role, Model model) {

		switch (role) {
		case "student":
			Student student = loginService.authenticationStudent(id, password);
			if (student != null) {
				model.addAttribute("student", student);
				return "/students/student-login";
			}
			break;
			
		case "teacher":
				Teacher teacher = loginService.authenticationTeacher(id, password);
				if (teacher != null) {
					model.addAttribute("teacher", teacher);
					return "/teachers/teacher-login";
				}
			break;

		case "admin":
			if (password.equals("admin1")) { 
				return "/courseSubject/course-subject-list" ;}
		 break;
		}
		return "/login/error_page";
	}
	
	
	@PostMapping("/student/save/{studentId}")
	public String updateStudent(@PathVariable Long studentId, @ModelAttribute("student") Student student, Model model) {
		Student existingStudent = studentRepository.findById(studentId).get();
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setPhoneNumber(student.getPhoneNumber());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setPassword(student.getPassword());
		studentRepository.save(existingStudent);
		return "/login/update-success";
	}
	
	@PostMapping("/teacher-save/{teacherId}")
	public String updateteacher(@PathVariable Long teacherId, @ModelAttribute("teacher") Teacher teacher, Model model) {
		Teacher existingTeacher = teacherRepository.findById(teacherId).get();
		existingTeacher.setFirstName(teacher.getFirstName());
		existingTeacher.setLastName(teacher.getLastName());
		existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
		existingTeacher.setEmail(teacher.getEmail());
		existingTeacher.setPassword(teacher.getPassword());
		teacherRepository.save(existingTeacher);
		return "/login/update-success";
	}	
}
