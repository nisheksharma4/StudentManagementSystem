package com.nsdev.studentmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.nsdev.studentmanagement.dto.PageResponseDTO;
import com.nsdev.studentmanagement.dto.StudentRequestDTO;
import com.nsdev.studentmanagement.dto.StudentResponseDTO;
import com.nsdev.studentmanagement.exception.CourseNotFoundException;
import com.nsdev.studentmanagement.exception.EntityNotFoundException;
import com.nsdev.studentmanagement.exception.StudentAlreadyExistsException;
import com.nsdev.studentmanagement.exception.StudentNotFoundException;
import com.nsdev.studentmanagement.mapper.StudentMapper;
import com.nsdev.studentmanagement.model.Course;
import com.nsdev.studentmanagement.model.Student;
import com.nsdev.studentmanagement.repository.CourseRepo;
import com.nsdev.studentmanagement.repository.StudentRepo;

@Service
public class StudentService {

	private StudentRepo studentRepo;
	private CourseRepo courseRepo;

	// constructor Dependency injection
	public StudentService(StudentRepo studentRepo, CourseRepo courseRepo) {
		this.studentRepo = studentRepo;
		this.courseRepo = courseRepo;
	}

	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}

	public StudentResponseDTO saveStudent(StudentRequestDTO dto) {
		if (studentRepo.existsByEmail(dto.getEmail())) {
			throw new StudentAlreadyExistsException("Student with email " + dto.getEmail() + " already exists");
		}

		// Fetching particular course using Course Id from Course Table
		Course course = courseRepo.findById(dto.getCourseId())
				.orElseThrow(() -> new CourseNotFoundException("Course Not Found"));

		Student student = StudentMapper.toEntity(dto, course);
		Student save = studentRepo.save(student); // save contain the new student record created with id
		return StudentMapper.toResponseDTO(save);

	}

	public StudentResponseDTO getStudentById(int id) {

		Student student = studentRepo.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Student with id :" + id + " not found."));

		StudentResponseDTO responseDTO = StudentMapper.toResponseDTO(student);
		return responseDTO;

	}

	public StudentResponseDTO updateStudent(int id, StudentRequestDTO dto) {
		// By using Id I found Existing Student...
		Student student1 = studentRepo.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Student with id : " + id + " not found."));
		// Update data by using setter
		student1.setFirstName(dto.getFirstName());
		student1.setLastName(dto.getLastName());
		student1.setContact(dto.getContact());
		student1.setAge(dto.getAge());
		student1.setEmail(dto.getEmail());

		// handle course
		if (dto.getCourseId() != null) {
			// Fetching the Course by using course Id.
			Course course = courseRepo.findById(dto.getCourseId()).orElseThrow(
					() -> new StudentNotFoundException("Course with id : " + dto.getCourseId() + " not found."));
			student1.setCourse(course);
		}
		Student saved = studentRepo.save(student1);
		return StudentMapper.toResponseDTO(saved);
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
		Student student = studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));

		studentRepo.delete(student);
	}

	// update student email

	public StudentResponseDTO updateStudentEmail(int id, String email) {
		Student student = studentRepo.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Student Not Found with : " + id + "."));

		if (email != null && studentRepo.existsByEmail(email)) {
			throw new StudentAlreadyExistsException("Student with " + email + " already Exist");
		}
		
		student.setEmail(email);		
		Student save = studentRepo.save(student);
		StudentResponseDTO response = StudentMapper.toResponseDTO(save);
		return response;
	}

	public StudentResponseDTO getStudentByIdAndLastName(int id, String lastName) {
		Student student = studentRepo.findByIdAndLastName(id, lastName).orElseThrow(() -> new StudentNotFoundException(
				"Student with id " + id + " and lastname " + lastName + " not found."));
		return StudentMapper.toResponseDTO(student);
	}

	public List<StudentResponseDTO> getStudentByLastName(String lastName) {
		// Fetch Students by lastname from Database
		List<Student> students = studentRepo.findByLastName(lastName);
		// convert Entity List to response DTO list
		return StudentMapper.toResponseDTOList(students);
	}

	// Fetch All Student
	public Page<StudentResponseDTO> getAllStudent(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Student> allStudents = studentRepo.findAll(pageRequest);
		return allStudents.map(student -> StudentMapper.toResponseDTO(student));
	}

	// Pagination with sorting { sortBy = ["firstName","lastName","Age",
	// "contact",etc] and direction = ascending or descending }
	public Page<StudentResponseDTO> getStudentsWithPaginationAndSorting(int page, int size, String sortBy,
			String direction) {

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Student> allStudent = studentRepo.findAll(pageable);
		return allStudent.map(student -> StudentMapper.toResponseDTO(student));
	}

	public List<StudentResponseDTO> getStudentByCourseId(int id) {
		List<Student> byCourseId = studentRepo.findByCourseId(id);
		return StudentMapper.toResponseDTOList(byCourseId);
	}

	public PageResponseDTO<StudentResponseDTO> getStudentsByCourseId(int courseId, int page, int size) {

		if (!courseRepo.existsById(courseId)) {
			throw new EntityNotFoundException("Course ID " + courseId + " not found");
		}
		PageRequest pr = PageRequest.of(page, size);

		Page<Student> studentPage = studentRepo.findByCourseId(courseId, pr);

		Page<StudentResponseDTO> dtoPage = studentPage.map(StudentMapper::toResponseDTO);

		PageResponseDTO<StudentResponseDTO> response = new PageResponseDTO<>();
		response.setContent(dtoPage.getContent());
		response.setPageNumber(dtoPage.getNumber());
		response.setPageSize(dtoPage.getSize());
		response.setTotalElements(dtoPage.getTotalElements());
		response.setTotalPages(dtoPage.getTotalPages());
		response.setLast(dtoPage.isLast());
		return response;
	}

}
