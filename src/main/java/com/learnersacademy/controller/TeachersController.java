package com.learnersacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.learnersacademy.dao.TeacherRepository;
import com.learnersacademy.model.Teacher;

@Controller
public class TeachersController {

	@Autowired
	TeacherRepository teacherRepository;

	@GetMapping("/teachers")
	public String listTeachers(Model model) {
		model.addAttribute("teachers", teacherRepository.findAll());
		return "/teachers/teachers";
	}

	@GetMapping("/teachers/new")
	public String createTeacherForm(Model model) { 
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "/teachers/create_teacher";
	}

	@PostMapping("/teachers")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
		teacherRepository.save(teacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/edit/{teacherId}")
	public String editTeacherForm(@PathVariable Long teacherId, Model model) {
		model.addAttribute("teacher", teacherRepository.findById(teacherId).get());
		return "/teachers/edit_teacher";
	}

	@PostMapping("/teachers/{teacherId}")
	public String updateTeacher(@PathVariable Long teacherId, @ModelAttribute("teacher") Teacher teacher, Model model) {
		Teacher existingTeacher = teacherRepository.findById(teacherId).get();
		existingTeacher.setFirstName(teacher.getFirstName());
		existingTeacher.setLastName(teacher.getLastName());
		existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
		existingTeacher.setEmail(teacher.getEmail());
		teacherRepository.save(existingTeacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/{teacherId}")
	public String deleteTeacher(@PathVariable Long teacherId) {
		teacherRepository.deleteById(teacherId);
		return "redirect:/teachers";
	}

}
