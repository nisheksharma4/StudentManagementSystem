package com.nsdev.studentmanagement.exception;

public class CourseNotFoundException extends RuntimeException{

	public CourseNotFoundException(String Message) {
		super(Message);
	}
}
