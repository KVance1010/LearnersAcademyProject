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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Long courseId;
	@Column(name = "course_name", unique = true)
	private String courseName;
	@Column(name = "description")
	private String description;
	
	
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST,
			   CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "courses_students",
	           joinColumns = @JoinColumn(name = "course_id"),
	           inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students;
	
	public Integer numberOfStudents() {
		return students.size();
	}
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST,
			   CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;

}
