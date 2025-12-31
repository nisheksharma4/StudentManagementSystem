package com.nsdev.studentmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.nsdev.studentmanagement.model.Student;
import com.nsdev.studentmanagement.utils.ResponseStructure;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ResponseStructure<Student>> handleStudentNotFound(StudentNotFoundException ex) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Student Not Found.");
		rs.setData(null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseStructure<Student>> handleRuntime(RuntimeException ex) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Student Not Found.");
		rs.setData(null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}
}
