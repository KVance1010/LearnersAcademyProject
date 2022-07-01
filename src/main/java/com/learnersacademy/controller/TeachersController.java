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
import com.learnersacademy.model.Subject;
import com.learnersacademy.model.Teacher;
import com.learnersacademy.service.CourseService;
import com.learnersacademy.service.SubjectService;
import com.learnersacademy.service.TeacherService;

@Controller
public class TeachersController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private CourseService courseService;

	// Main Teacher page
	@GetMapping("/teachers")
	public String listTeachers(Model model) {
		model.addAttribute("teachers", teacherService.getAllTeachers());
		return "/teachers/teachers";
	}

	// Adding a new teacher
	@GetMapping("/teachers/new")
	public String createTeacherForm(Model model) {
		Teacher teacher = new Teacher();
		List<Subject> subject = (List<Subject>) subjectService.getAllSubjects();
		model.addAttribute("teacher", teacher);
		model.addAttribute("subject", subject);
		return "/teachers/create-teacher";
	}

	// Teacher Save method
	@PostMapping("/teachers-save")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teacher, Model model) {
		teacherService.saveTeacher(teacher);
		return "redirect:/teachers";
	}
	
	// Teacher/Course Save method
		@PostMapping("/teachers-save-course")
		public String saveCourse(@ModelAttribute("subject") Subject subject, Model model) {
			subjectService.saveSubject(subject);
			return "redirect:/teachers";
		}

	// Editing the Teacher form
	@GetMapping("/teachers/edit/{teacherId}")
	public String editTeacherForm(@PathVariable Long teacherId, Model model) {
		model.addAttribute("subject", subjectService.getAllSubjects());
		model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
		return "/teachers/edit-teacher";
	}

	// Updating the Teacher form
	@PostMapping("/teachers/{teacherId}")
	public String updateTeacher(@PathVariable Long teacherId, @ModelAttribute("teacher") Teacher teacher, Model model) {
		Teacher existingTeacher = teacherService.getTeacherById(teacherId);
		existingTeacher.setFirstName(teacher.getFirstName());
		existingTeacher.setLastName(teacher.getLastName());
		existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
		existingTeacher.setEmail(teacher.getEmail());
		existingTeacher.setPassword(teacher.getPassword());
		teacherService.saveTeacher(existingTeacher);
		return "redirect:/teachers";
	}

	// Deleting the teacher form
	@GetMapping("/teachers/{teacherId}")
	public String deleteTeacher(@PathVariable Long teacherId) {
		teacherService.deleteTeacherById(teacherId);
		return "redirect:/teachers";
	}

	// Adding a subject to what the teacher teaches
	@GetMapping("/teacher-subjects/{teacherId}")
	public String addSubject(@PathVariable Long teacherId, Model model) {
		Teacher existingTeacher = teacherService.getTeacherById(teacherId);
		List<Subject> teachersSubject = subjectService.getSubjectByTeacherID(teacherId);
//      List<Course> course = courseService.findCoursesByTeacherId(teacherId);
		List<Subject> allSubjects = subjectService.getAllSubjects();
		model.addAttribute("allSubjects", allSubjects);
//      model.addAttribute("course", course);
		model.addAttribute("teacher", existingTeacher);
		model.addAttribute("teachersSubjects", teachersSubject);
		return "/teachers/teachers-subjects";
	}

	// Adding a subject
	@GetMapping("/add-subject/{teacherId}/{subjectId}")
	public String addsubject(@PathVariable Long teacherId, @PathVariable Long subjectId) {
		Subject subject = subjectService.getSubjectById(subjectId);
		Teacher teacher = teacherService.getTeacherById(teacherId);
		teacher.addSubject(subject);
		teacherService.saveTeacher(teacher);
		return "redirect:/teachers";
	}
	
	   // Adding a course to subject
		@GetMapping("/subject-add-course/{subjectId}")
		public String addCourseToSubject(@PathVariable Long subjectId, Model model) {
			Subject subject = subjectService.getSubjectById(subjectId);
			List<Course> course = courseService.getCoursesBySubjectId(subjectId);
			model.addAttribute("subject", subject);
			model.addAttribute("course", course);
			return "/teachers/add-course-teacher-subject";
		}

	// Deletes subject from teacher
	@GetMapping("/delete-subject/{teacherId}/{subjectId}")
	public String deleteStudentCourse(@PathVariable Long teacherId, @PathVariable Long subjectId) {
		Subject subject = subjectService.getSubjectById(subjectId);
		Teacher teacher = teacherService.getTeacherById(teacherId);
		teacher.deleteSubject(subject);
		teacherService.saveTeacher(teacher);
		return "redirect:/teachers";
	}

}
