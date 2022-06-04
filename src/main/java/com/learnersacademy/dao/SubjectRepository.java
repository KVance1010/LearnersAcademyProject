package com.learnersacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnersacademy.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
