package com.learnersacademy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learnersacademy.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

   @Query (nativeQuery = true, value = "SELECT * FROM courses AS c WHERE c.subject_id = :id ")
   public List<Course> findBySubjectId(Long id);

	
}
