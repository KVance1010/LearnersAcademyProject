package com.learnersacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnersacademy.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}