package com.learnersacademy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learnersacademy.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	// should be left join t.first_name AS firstName, t.last_name AS lastName
	@Query(nativeQuery = true, value = "SELECT  * FROM teachers AS t "
			+ "CROSS JOIN teachers_subjects AS ts  ON ts.teacher_id = t.teacher_id " + "where subject_id = :id ")
	public List<Teacher> findTeacherBySubjectId(Long id);

	// this will tell if there is a teacher assigned to a course
	@Query(nativeQuery = true, value = "SELECT * FROM teachers AS t "
			+ "INNER JOIN teachers_subjects AS ts  ON ts.teacher_id = t.teacher_id "
			+ "INNER JOIN subjects AS s USING (subject_id) " + "INNER JOIN courses AS c USING (subject_id) "
			+ "WHERE t.teacher_id = 1? AND c.course_id = 2?")
	public Teacher findTeacherByCourseId(Long id);

	// Find Teacher by Id and Password
	//@Query(nativeQuery = true, value = "SELECT * FROM teachers  AS t WHERE t.teacher_id = : teacherId AND t.password = :password")
	Optional<Teacher> findByTeacherIdAndPassword(Long teacherId, String password);
}
