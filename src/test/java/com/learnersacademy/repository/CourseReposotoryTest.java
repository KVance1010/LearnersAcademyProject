package com.learnersacademy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learnersacademy.dao.CourseRepository;

@SpringBootTest
public class CourseReposotoryTest {
	
	@Autowired
	CourseRepository courseRepository;
	

}
