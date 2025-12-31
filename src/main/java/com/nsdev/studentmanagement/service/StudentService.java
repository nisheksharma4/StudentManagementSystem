package com.nsdev.studentmanagement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nsdev.studentmanagement.exception.StudentAlreadyExistsException;
import com.nsdev.studentmanagement.exception.StudentNotFoundException;
import com.nsdev.studentmanagement.model.Student;
import com.nsdev.studentmanagement.repository.StudentRepo;

@Service
public class StudentService {

	private StudentRepo studentRepo;
	
	
	//constructor Dependency injection
	public StudentService(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public Student getStudentById(int id) {
		
		 Optional<Student> optional = studentRepo.findById(id);
		 if(optional.isPresent()) {
			 return optional.get();
		 }else {
			 throw new StudentNotFoundException("Student with Id : "+id + " Not Found!");
		 }
	}
	
	public Student updateStudent(int id, Student student) {
		Optional<Student> optional = studentRepo.findById(id);
		if(optional.isPresent()) {
			student.setId(id);
			return studentRepo.save(student);
			
		}else {
			throw new StudentNotFoundException("Student Not Found!!");
		}
	}
	
//	public void deleteStudent(int id) {
//		Optional<Student> optional = studentRepo.findById(id);		
//		if(optional.isPresent()) {
//			studentRepo.delete(optional.get());
//		}else {
//			throw new RuntimeException("Student with "+id+" not found.");
//		}
//	}
	
	public void deleteStudent(int id) {

	  	    Student student = studentRepo.findById(id)
	  	    		.orElseThrow(() -> new StudentNotFoundException("Student not found"));


	    studentRepo.delete(student);
	}
	
	//update student email
	
	public Student updateStudentEmail(int id, String email) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not Found with : "+id+"."));
		
		if(email != null && studentRepo.existsByEmail(email)) {
			throw new StudentAlreadyExistsException("Student with "+email+" already Exist");
		}
		
		student.setEmail(email);
		return studentRepo.save(student);
	}

	
}
