package com.nsdev.studentmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO {
	
	private Integer id;
	private String name;
	private Integer age;
	private String email;
	private Long contact;
	private String courseName;
}
