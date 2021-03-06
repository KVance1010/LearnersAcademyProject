package com.learnersacademy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learnersacademy.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM courses AS c WHERE c.subject_id = :id ")
	public List<Course> findBySubjectId(Long id);

//	// shows a list of subjects by Teacher
//	@Query(nativeQuery = true, value = "SELECT * FROM teachers AS t "
//			+ "INNER JOIN teachers_subjects AS ts USING (teacher_id) " + "INNER JOIN subjects AS s USING (subject_id) "
//			+ "RIGHT JOIN courses AS c on c.subject_id = s.subject_id " + "WHERE T.teacher_id = :teacherId")
//	public List<Course> findCoursesByTeacherId(Long teacherId);

}
