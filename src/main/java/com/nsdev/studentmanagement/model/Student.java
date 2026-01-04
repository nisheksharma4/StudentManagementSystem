package com.nsdev.studentmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	@NotBlank(message = "First Name is required")
	@Size(min = 2, max = 50, message = "Name must be 2-50 characters")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Size(max = 30, message = "Last name must be <=30 chars")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull
	private Integer age;
	
	@Column(unique = true)
	@Positive(message = "Contact must be Positive.")
	private Long contact;
	
	@Email
	@Column(unique = true, nullable = true) 
	private String email;
	
	 
}
