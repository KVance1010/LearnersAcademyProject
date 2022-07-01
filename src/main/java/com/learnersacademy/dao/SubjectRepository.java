package com.learnersacademy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learnersacademy.dto.TeacherCourse;
import com.learnersacademy.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	// shows a list of subjects by Teacher
	@Query(nativeQuery = true, value = "SELECT * FROM teachers AS t "
			+ "INNER JOIN teachers_subjects AS ts USING (teacher_id) "
			+ "RIGHT JOIN subjects AS s USING (subject_id) WHERE t.teacher_id = :teacherId")
	public List<Subject> findSubjectsByTeacherId(Long teacherId);

	// populates the TeacherCourseDTO
	@Query(nativeQuery = true, value = " SELECT t.last_name AS lastName, t.first_name AS firstName, "
			+ "c.course_id AS courseId, c.course_name AS courseName, count(DISTINCT cs.student_id) "
			+ "as numStudents FROM teachers AS t "
			+ "INNER JOIN teachers_subjects AS ts ON ts.teacher_id = t.teacher_id "
			+ "INNER JOIN subjects AS s USING (subject_id) " + "INNER JOIN courses AS c USING (subject_id) "
			+ "INNER JOIN courses_students AS cs USING (course_id) " + "WHERE s.subject_id = :subjectId "
			+ "Group by c.course_name")
	public List<TeacherCourse> findTeacherAndCourseBySubject(Long subjectId);
}
