package com.learnersacademy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnersacademy.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

//	// should be left join t.first_name AS firstName, t.last_name AS lastName
//	@Query(nativeQuery = true, value = "SELECT  * FROM teachers AS t "
//			+ "CROSS JOIN teachers_subjects AS ts  ON ts.teacher_id = t.teacher_id " + "where subject_id = :id ")
//	public List<Teacher> findTeacherBySubjectId(Long id);


	// Find Teacher by Id and Password
	Optional<Teacher> findByTeacherIdAndPassword(Long teacherId, String password);
	
}
