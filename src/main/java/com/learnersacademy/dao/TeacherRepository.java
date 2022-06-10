package com.learnersacademy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.learnersacademy.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	//should be left join
	  @Query (nativeQuery = true, value = "SELECT t.first_name AS firstName, t.last_name AS lastName FROM teachers AS t "
	  		+ "Cross JOIN teachers_subjects AS ts  ON ts.teacher_id = t.teacher_id "
	  		+ "where subject_id = :id ")
	   public List<Teacher> findBySubjectId(Long id);
}
