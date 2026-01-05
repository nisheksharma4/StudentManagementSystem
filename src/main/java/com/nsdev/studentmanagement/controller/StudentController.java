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

import com.nsdev.studentmanagement.dto.PageResponseDTO;
import com.nsdev.studentmanagement.dto.StudentRequestDTO;
import com.nsdev.studentmanagement.dto.StudentResponseDTO;
import com.nsdev.studentmanagement.model.Student;
import com.nsdev.studentmanagement.service.StudentService;
import com.nsdev.studentmanagement.utils.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/student")
public class StudentController {
	
	private StudentService studentService;
		
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> saveStudent(@Valid @RequestBody StudentRequestDTO dto){
		
		StudentResponseDTO response = studentService.saveStudent(dto);
		
		ResponseStructure<StudentResponseDTO> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.CREATED.value());
		rs.setMessage("Student Created Successfully");
		rs.setData(response);
		return ResponseEntity.status(HttpStatus.CREATED).body(rs);
	}
	
//	@PostMapping("/create")
//	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student){
//		Student saved =  studentService.saveStudent(student);
//		ResponseStructure<Student> responseStructure = new ResponseStructure<>();
//		responseStructure.setStatus(HttpStatus.CREATED.value());
//		responseStructure.setMessage("Student Added Successfully!");
//		responseStructure.setData(saved);
//		return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
//	}
//	@PostMapping("/create")
//	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
//	    Student saved = studentService.saveStudent(student);
//	    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> getStudentById(@PathVariable int id) {
		StudentResponseDTO responseDTO = studentService.getStudentById(id);
		ResponseStructure<StudentResponseDTO> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student With Id : "+id+" Found.");
		rs.setData(responseDTO);
		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(@PathVariable int id, @RequestBody StudentRequestDTO dto) {
		
		StudentResponseDTO responseDTO = studentService.updateStudent(id, dto);
		
		ResponseStructure<StudentResponseDTO> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student With id : "+id+" updated Succesfully.");
		rs.setData(responseDTO);
		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Void>> deleteStudent(@PathVariable int id) {
		studentService.deleteStudent(id);
		
		ResponseStructure<Void> rs = new ResponseStructure<>();
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
	
	
	// Find Student by using his/her Id and Last Name
	@GetMapping
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> studentById(@RequestParam int id, @RequestParam String lastName) {
		StudentResponseDTO response = studentService.getStudentByIdAndLastName(id, lastName);
		ResponseStructure<StudentResponseDTO> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student with id "+id+" and lastname "+lastName+" found.");
		rs.setData(response);
		return ResponseEntity.status(HttpStatus.OK).body(rs);
	}
	
	//Find Student by using his/her lastName
	@GetMapping("/lname")
	public ResponseEntity<ResponseStructure<List<StudentResponseDTO>>> getStudentByLastName(@RequestParam String lastName) {
		// Receives list of student response DTOs filtered by last name 
		List<StudentResponseDTO> response = studentService.getStudentByLastName(lastName);
		
		ResponseStructure<List<StudentResponseDTO>> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage(response.size()+" student records found with "+lastName+".");
		rs.setData(response);
		return ResponseEntity.ok(rs);
	}
	
	//Fetched all student from database using DTO Mapper and Pagination
	@GetMapping("/page")
	public ResponseEntity<ResponseStructure<PageResponseDTO<StudentResponseDTO>>> getAllStudent(@RequestParam int page, @RequestParam int size) {
		Page<StudentResponseDTO> allStudent = studentService.getAllStudent(page, size);
		
		PageResponseDTO<StudentResponseDTO> pageResponseDto = new PageResponseDTO<>();
		pageResponseDto.setContent(allStudent.getContent());
		pageResponseDto.setPageNumber(allStudent.getNumber());
		pageResponseDto.setPageSize(allStudent.getSize());
		pageResponseDto.setTotalElements(allStudent.getTotalElements());
		pageResponseDto.setTotalPages(allStudent.getTotalPages());
		pageResponseDto.setLast(allStudent.isLast());
		
		ResponseStructure<PageResponseDTO<StudentResponseDTO>> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage(allStudent.getTotalElements()+" Student fetched Successfully");
		rs.setData(pageResponseDto);
		
		return ResponseEntity.ok(rs);
	}
	
	@GetMapping("/page-sort")
	public ResponseEntity<ResponseStructure<Page<StudentResponseDTO>>> getStudentsWithPaginationAndSorting(
	        @RequestParam int page,
	        @RequestParam int size,
	        @RequestParam String sortBy,
	        @RequestParam String direction) {	    
	            Page<StudentResponseDTO> studentsWithPaginationAndSorting = studentService.getStudentsWithPaginationAndSorting(page, size, sortBy, direction);
	            

	    ResponseStructure<Page<StudentResponseDTO>> rs = new ResponseStructure<>();
	    rs.setStatus(HttpStatus.OK.value());
	    rs.setMessage("Students fetched with pagination and sorting");
	    rs.setData(studentsWithPaginationAndSorting);

	    return ResponseEntity.ok(rs);
	}
	
	//Student with Particular Course ID will be fetched not paginated
	@GetMapping("/courseid/{courseid}")
	public ResponseEntity<ResponseStructure<List<StudentResponseDTO>>> getStudentsByCourseId(@PathVariable int courseid) {
		List<StudentResponseDTO> studentByCourseId = studentService.getStudentByCourseId(courseid);
		String courseName = studentByCourseId.get(0).getCourseName();
		ResponseStructure<List<StudentResponseDTO>> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student with course id and course name :- "+courseid+" : " +courseName+ " found.");
		rs.setData(studentByCourseId);
		
		return ResponseEntity.ok(rs);
	}
	
	@GetMapping("/courseIdPage/{courseid}")
	public ResponseEntity<ResponseStructure<PageResponseDTO<StudentResponseDTO>>> getStudentByCourseIdAndPage(@PathVariable int courseid, @RequestParam int page, @RequestParam int size) {
		
		PageResponseDTO<StudentResponseDTO> students = studentService.getStudentsByCourseId(courseid, page, size);
		
		ResponseStructure<PageResponseDTO<StudentResponseDTO>> rs = new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Student found in page with courseid: "+courseid);
		rs.setData(students);
		return ResponseEntity.ok(rs);
	}

}
