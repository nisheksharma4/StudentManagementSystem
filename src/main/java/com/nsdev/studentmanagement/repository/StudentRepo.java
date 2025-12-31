package com.nsdev.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsdev.studentmanagement.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	boolean existsByEmail(String email);
}
