package com.nsdev.studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEmailUpdateDTO {
	
	@NotBlank
	@Email(message = "Email is Required.")
	private String email;
}
