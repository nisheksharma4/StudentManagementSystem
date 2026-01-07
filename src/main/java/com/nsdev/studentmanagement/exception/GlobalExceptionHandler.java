package com.nsdev.studentmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nsdev.studentmanagement.model.Student;
import com.nsdev.studentmanagement.utils.ResponseStructure;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ResponseStructure<Student>> handleStudentNotFound(StudentNotFoundException ex) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage(ex.getMessage()); // retrieves the message passed to super(message) in the exception constructor
										// (inherited from Throwable)
		rs.setData(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ResponseStructure<Student>> handleEntityNotFoundException(EntityNotFoundException ex) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage(ex.getMessage()); // retrieves the message passed to super(message) in the exception constructor
										// (inherited from Throwable)
		rs.setData(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}

	@ExceptionHandler(StudentAlreadyExistsException.class)
	public ResponseEntity<ResponseStructure<Student>> studentAlreadyExistsException(StudentAlreadyExistsException ex) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage(ex.getMessage()); // retrieves the message passed to super(message) in the exception constructor
										// (inherited from Throwable)
		rs.setData(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ResponseStructure<Student>> courseNotFoundExceptiion(CourseNotFoundException ex) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage(ex.getMessage()); // retrieves the message passed to super(message) in the exception constructor
										// (inherited from Throwable)
		rs.setData(null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseStructure<String>> handlePathVariableError(MethodArgumentTypeMismatchException ex) {
        ResponseStructure<String> rs = new ResponseStructure<>();
        rs.setStatus(HttpStatus.BAD_REQUEST.value());
        rs.setMessage("Invalid request: path variable or parameter missing/incorrect");
        rs.setData(null);
        return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
    }

}
