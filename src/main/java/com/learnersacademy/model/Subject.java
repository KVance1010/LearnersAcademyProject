package com.learnersacademy.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private Long subjectId;
	@Column(name = "subject_name")
	private String subjectName;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "teachers_subjects",
	           joinColumns = @JoinColumn(name = "subject_id"),
	           inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private List<Teacher> teachers;

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subject")
	private Set<Course> courses = new HashSet<>();
}
