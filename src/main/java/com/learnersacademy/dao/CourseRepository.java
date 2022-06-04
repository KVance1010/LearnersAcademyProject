package com.learnersacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnersacademy.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
