package com.cheerios.security.student.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class ManagementController {
	
	@GetMapping("{studentId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void getStudentById(@PathVariable("studentId") Integer studentId) {
		System.out.println("Hello world");
	}
	
	@DeleteMapping("{studentId}")
	@PreAuthorize("hasAnyAuthority('course:read', 'course:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("Hello delete");
	}

}
