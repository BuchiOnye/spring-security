package com.cheerios.security.student.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheerios.security.student.entity.Student;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	List<Student> students = Arrays.asList(
			new Student(1, "Anna-Nichole"),
			new Student(2, "Sandy Lala"),
			new Student(3, "April Cheerios")
			);
	
	@GetMapping("{studentId}")
	public Student getStudentById(@PathVariable("studentId") Integer studentId) {
		return students.stream()
				.filter(student -> studentId.equals(student.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Student does not exist"));
	}

}
