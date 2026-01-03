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
		rs.setMessage(ex.getMessage()); // retrieves the message passed to super(message) in the exception constructor (inherited from Throwable)
		rs.setData(null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseStructure<Student>> handleRuntime(RuntimeException ex) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage(ex.getMessage()); // retrieves the message passed to super(message) in the exception constructor (inherited from Throwable)
		rs.setData(null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ResponseStructure<Student>> handleEntityNotFoundException(EntityNotFoundException ex) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage(ex.getMessage()); // retrieves the message passed to super(message) in the exception constructor (inherited from Throwable)
		rs.setData(null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}
	
}
