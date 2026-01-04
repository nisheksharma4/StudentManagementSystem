package com.nsdev.studentmanagement.mapper;

import com.nsdev.studentmanagement.dto.StudentRequestDTO;
import com.nsdev.studentmanagement.dto.StudentResponseDTO;
import com.nsdev.studentmanagement.model.Course;
import com.nsdev.studentmanagement.model.Student;

public class StudentMapper {
	
	//RequestDTO -> Entity
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
	
	//Entity -> ResponseDTO
	public static StudentResponseDTO toResponseDTO(Student student) {
		StudentResponseDTO dto = new StudentResponseDTO();
		dto.setId(student.getId());
		dto.setName(student.getFirstName() + " "+student.getLastName());
		dto.setAge(student.getAge());
		dto.setEmail(student.getEmail());
		dto.setContact(student.getContact());
		dto.setCourseName(student.getCourse().getCourseName());
		return dto;
	}
}
