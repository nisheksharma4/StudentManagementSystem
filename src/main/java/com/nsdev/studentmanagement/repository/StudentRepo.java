package com.nsdev.studentmanagement.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nsdev.studentmanagement.model.Student;


import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	boolean existsByEmail(String email);
	
	Optional<Student> findByIdAndLastName(int id, String lastName);
	
	 List<Student> findByLastName(String lastName);
	 
	 //pagination
	 Page<Student> findAll(Pageable pageable);


}
