package com.learnersacademy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long studentId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	
	
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST,
			   CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "courses_students",
	           joinColumns = @JoinColumn(name = "student_id"),
	           inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;
	
	public void deleteCourse(Course course) {
		courses.remove(course);
	}
	public void addCourse(Course course) {
		courses.add(course);
	}

}
