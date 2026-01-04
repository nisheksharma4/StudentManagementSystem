package com.nsdev.studentmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nsdev.studentmanagement.model.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

	
}
