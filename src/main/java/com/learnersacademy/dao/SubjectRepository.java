package com.learnersacademy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learnersacademy.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	    // shows a list of subjects by Teacher
		@Query(nativeQuery = true, value = "SELECT * FROM teachers AS t "
				+ "INNER JOIN teachers_subjects AS ts USING (teacher_id) "
				+ "RIGHT JOIN subjects AS s USING (subject_id) WHERE t.teacher_id = :teacherId")
		  public List<Subject> findSubjectsByTeacherId(Long teacherId);
}
