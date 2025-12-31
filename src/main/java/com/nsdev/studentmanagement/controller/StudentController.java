package com.nsdev.studentmanagement.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsdev.studentmanagement.model.Student;
import com.nsdev.studentmanagement.service.StudentService;
import com.nsdev.studentmanagement.utils.ResponseStructure;

@RestController
@RequestMapping("api/student")
public class StudentController {
	
	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student){
		Student saved =  studentService.saveStudent(student);
		ResponseStructure responseStructure = new ResponseStructure();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Student Added Successfully!");
		responseStructure.setData(saved);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
	}
//	@PostMapping("/create")
//	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
//	    Student saved = studentService.saveStudent(student);
//	    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Student>> getStudentById(@PathVariable int id) {
		Student studentById = studentService.getStudentById(id);
		ResponseStructure rs = new ResponseStructure();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student With Id : "+id+" Found.");
		rs.setData(studentById);
		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure> updateStudent(@PathVariable int id, @RequestBody Student student) {
		Student updateStudent = studentService.updateStudent(id, student);
		ResponseStructure rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student With id : "+id+" updated Succesfully.");
		rs.setData(updateStudent);
		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure> deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
		
		ResponseStructure rs= new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student With Id : "+id+" deleted Successfully.");
		rs.setData(null);
		
		return ResponseEntity.status(HttpStatus.OK).body(rs);	
		
	}
	
	@PatchMapping("/{id}/email")
	public ResponseEntity<Student> updateStudentEmail(@PathVariable int id, @RequestBody Map<String, String> body) throws IllegalAccessException {
		String email = body.get("email");
		if(email == null || email.isEmpty()) {
			throw new IllegalAccessException("Email is required");
		}
		Student updateStudentEmail = studentService.updateStudentEmail(id, email);
		return ResponseEntity.ok(updateStudentEmail);
	}
	
}
