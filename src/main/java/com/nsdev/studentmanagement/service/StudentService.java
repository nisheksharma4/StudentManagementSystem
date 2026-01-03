package com.nsdev.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.nsdev.studentmanagement.exception.EntityNotFoundException;
import com.nsdev.studentmanagement.exception.StudentAlreadyExistsException;
import com.nsdev.studentmanagement.exception.StudentNotFoundException;
import com.nsdev.studentmanagement.model.Student;
import com.nsdev.studentmanagement.repository.CourseRepo;
import com.nsdev.studentmanagement.repository.StudentRepo;

@Service
public class StudentService {

	private StudentRepo studentRepo;
	private CourseRepo courseRepo;
	
	
	//constructor Dependency injection
	public StudentService(StudentRepo studentRepo, CourseRepo courseRepo) {
		this.studentRepo = studentRepo;
		this.courseRepo = courseRepo;
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
	
	public Student getStudentByIdAndLastName(int id, String lastName) {
		return studentRepo.findByIdAndLastName(id, lastName)
				.orElseThrow(() -> new StudentNotFoundException("Student with id "+id+ " and lastname "+lastName+" not found."));
	}
	
	public List<Student> getStudentByLastName(String lastName) {
		 List<Student> students = studentRepo.findByLastName(lastName);
		 
		 if(students.isEmpty()) {
			 throw new StudentNotFoundException("Student with Last Name "+lastName+" not found!");
		 }
		 
		 return students;
	}
	
	public Page<Student> getAllStudent(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return studentRepo.findAll(pageRequest);
	}
	
	public Page<Student> getStudentsWithPaginationAndSorting(int page, int size, String sortBy, String direction) {
	    Sort sort = direction.equalsIgnoreCase("desc")
	            ? Sort.by(sortBy).descending()
	            : Sort.by(sortBy).ascending();
	    Pageable pageable = PageRequest.of(page, size, sort);
	    return studentRepo.findAll(pageable);
	}
	
	public List<Student> getStudentByCourseId(int id){
		return studentRepo.findByCourseId(id);
	}
	
	public Page<Student> getStudentsByCourseId(int courseId, int page, int size) {
		
	    
	    if (!courseRepo.existsById(courseId)) {
	        throw new EntityNotFoundException("Course ID " + courseId + " not found");
	    }
	    PageRequest pr = PageRequest.of(page, size);
	    return studentRepo.findByCourseId(courseId, pr);
	}


	
}
