package com.nsdev.studentmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO {
	
	private int id;
	private String name;
	private int age;
	private String email;
	private Long contact;
	private String courseName;
}
