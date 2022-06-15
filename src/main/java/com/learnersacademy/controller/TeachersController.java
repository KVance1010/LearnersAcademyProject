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
import com.learnersacademy.dao.TeacherRepository;
import com.learnersacademy.model.Course;
import com.learnersacademy.model.Subject;
import com.learnersacademy.model.Teacher;

@Controller
public class TeachersController {

	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	CourseRepository courseRepository;

	// Main Teacher page
	@GetMapping("/teachers")
	public String listTeachers(Model model) {
		model.addAttribute("teachers", teacherRepository.findAll());
		return "/teachers/teachers";
	}

	// Adding a new teacher
	@GetMapping("/teachers/new")
	public String createTeacherForm(Model model) {
		Teacher teacher = new Teacher();
		List<Subject> subject = (List<Subject>) subjectRepository.findAll();
		model.addAttribute("teacher", teacher);
		model.addAttribute("subject", subject);
		return "/teachers/create-teacher";
	}

	// Teacher Save method
	@PostMapping("/teachers-save")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teacher, Model model) {
		teacherRepository.save(teacher);
		return "redirect:/teachers";
	}
	
	// Teacher/Course Save method
		@PostMapping("/teachers-save-course")
		public String saveCourse(@ModelAttribute("subject") Subject subject, Model model) {
			subjectRepository.save(subject);
			return "redirect:/teachers";
		}

	// Editing the Teacher form
	@GetMapping("/teachers/edit/{teacherId}")
	public String editTeacherForm(@PathVariable Long teacherId, Model model) {
		model.addAttribute("subject", subjectRepository.findAll());
		model.addAttribute("teacher", teacherRepository.findById(teacherId).get());
		return "/teachers/edit-teacher";
	}

	// Updating the Teacher form
	@PostMapping("/teachers/{teacherId}")
	public String updateTeacher(@PathVariable Long teacherId, @ModelAttribute("teacher") Teacher teacher, Model model) {
		Teacher existingTeacher = teacherRepository.findById(teacherId).get();
		existingTeacher.setFirstName(teacher.getFirstName());
		existingTeacher.setLastName(teacher.getLastName());
		existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
		existingTeacher.setEmail(teacher.getEmail());
		existingTeacher.setPassword(teacher.getPassword());
		teacherRepository.save(existingTeacher);
		return "redirect:/teachers";
	}

	// Deleting the teacher form
	@GetMapping("/teachers/{teacherId}")
	public String deleteTeacher(@PathVariable Long teacherId) {
		teacherRepository.deleteById(teacherId);
		return "redirect:/teachers";
	}

	// Adding a subject to what the teacher teaches
	@GetMapping("/teacher-subjects/{teacherId}")
	public String addSubject(@PathVariable Long teacherId, Model model) {
		Teacher existingTeacher = teacherRepository.findById(teacherId).get();
		List<Subject> teachersSubject = subjectRepository.findSubjectsByTeacherId(teacherId);
//      List<Course> course = courseRepository.findCoursesByTeacherId(teacherId);
		List<Subject> allSubjeects = subjectRepository.findAll();
		model.addAttribute("allSubjects", allSubjeects);
//      model.addAttribute("course", course);
		model.addAttribute("teacher", existingTeacher);
		model.addAttribute("teachersSubjects", teachersSubject);
		return "/teachers/teachers-subjects";
	}

	// Adding a subject
	@GetMapping("/add-subject/{teacherId}/{subjectId}")
	public String addsubject(@PathVariable Long teacherId, @PathVariable Long subjectId) {
		Subject subject = subjectRepository.findById(subjectId).get();
		Teacher teacher = teacherRepository.findById(teacherId).get();
		teacher.addSubject(subject);
		teacherRepository.save(teacher);
		return "redirect:/teachers";
	}
	
	   // Adding a course to subject
		@GetMapping("/subject-add-course/{subjectId}")
		public String addCourseToSubject(@PathVariable Long subjectId, Model model) {
			Subject subject = subjectRepository.findById(subjectId).get();
			List<Course> course = courseRepository.findBySubjectId(subjectId);
			model.addAttribute("subject", subject);
			model.addAttribute("course", course);
			return "/teachers/add-course-teacher-subject";
		}

	// Deletes subject from teacher
	@GetMapping("/delete-subject/{teacherId}/{subjectId}")
	public String deleteStudentCourse(@PathVariable Long teacherId, @PathVariable Long subjectId) {
		Subject subject = subjectRepository.findById(subjectId).get();
		Teacher teacher = teacherRepository.findById(teacherId).get();
		teacher.deleteSubject(subject);
		teacherRepository.save(teacher);
		return "redirect:/teachers";
	}

}
