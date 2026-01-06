package com.nsdev.studentmanagement.mapper;

import java.util.List;

import com.nsdev.studentmanagement.dto.StudentRequestDTO;
import com.nsdev.studentmanagement.dto.StudentResponseDTO;
import com.nsdev.studentmanagement.model.Course;
import com.nsdev.studentmanagement.model.Student;

public class StudentMapper {

	// RequestDTO -> Entity
	public static Student toEntity(StudentRequestDTO dto, Course course) {
		Student student = new Student();
		student.setFirstName(dto.getFirstName());
		student.setLastName(dto.getLastName());
		student.setAge(dto.getAge());
		student.setEmail(dto.getEmail());
		student.setContact(dto.getContact());
		student.setCourse(course);
		return student;
	}

	// Entity -> ResponseDTO
	public static StudentResponseDTO toResponseDTO(Student student) {
		StudentResponseDTO dto = new StudentResponseDTO();
		dto.setId(student.getId());
		dto.setName(student.getFirstName() + " " + student.getLastName());
		dto.setAge(student.getAge());
		dto.setEmail(student.getEmail());
		dto.setContact(student.getContact());
		// student.getCourse() returns course entity, so we extract only the getName()
		// to match the string field CourseName in the StudentResponseDTO
		dto.setCourseName(student.getCourse().getCourseName());
		return dto;
	}

	public static List<StudentResponseDTO> toResponseDTOList(List<Student> students) {
		return students.stream().map(student -> StudentMapper.toResponseDTO(student)).toList();
	}

}
