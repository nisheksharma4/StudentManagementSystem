package com.nsdev.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsdev.studentmanagement.model.Course;
import com.nsdev.studentmanagement.model.Student;

public interface CourseRepo extends JpaRepository<Course, Integer> {

	
}
