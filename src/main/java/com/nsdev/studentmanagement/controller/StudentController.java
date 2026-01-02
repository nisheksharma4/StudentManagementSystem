package com.nsdev.studentmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		ResponseStructure<Student> responseStructure = new ResponseStructure<>();
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
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student With Id : "+id+" Found.");
		rs.setData(studentById);
		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@PathVariable int id, @RequestBody Student student) {
		Student updateStudent = studentService.updateStudent(id, student);
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student With id : "+id+" updated Succesfully.");
		rs.setData(updateStudent);
		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
		
		ResponseStructure<Student> rs = new ResponseStructure<>();
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
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Student>> studentById(@RequestParam int id, @RequestParam String lastname) {
		Student studentById = studentService.getStudentByIdAndLastName(id, lastname);
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student with id "+id+" and lastname "+lastname+" found.");
		rs.setData(studentById);
		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}
	
	@GetMapping("/lname")
	public ResponseEntity<ResponseStructure<List<Student>>> getStudentByLastName(@RequestParam String lastName) {
		List<Student> students= studentService.getStudentByLastName(lastName);
		
		ResponseStructure<List<Student>> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage(students.size()+" student records found with "+lastName+".");
		rs.setData(students);
		return ResponseEntity.ok(rs);
	}
	
	@GetMapping("/page")
	public ResponseEntity<ResponseStructure<Page<Student>>> getAllStudent(@RequestParam int page, @RequestParam int size) {
		Page<Student> allStudent = studentService.getAllStudent(page, size);
		
		ResponseStructure<Page<Student>> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student fetch Successfully");
		rs.setData(allStudent);
		
		return ResponseEntity.ok(rs);
	}
		
}
