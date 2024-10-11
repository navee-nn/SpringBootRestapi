package com.example.SpringBootAPICrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootAPICrud.dto.Student;
import com.example.SpringBootAPICrud.helper.ResponseStructure;
import com.example.SpringBootAPICrud.service.StudentApiService;

@RestController
@RequestMapping("/students")
public class StudentApiController {

	@Autowired
	StudentApiService service;

	@PostMapping("/students")
	public ResponseEntity<ResponseStructure<Student>> addStudent(@RequestBody Student student) {
		return service.addStudent(student);
	}

	@GetMapping("students")
	public ResponseEntity<ResponseStructure<List<Student>>> fetchAll() {
		return service.fetchAll();
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<ResponseStructure<Student>> fetchEmployeeById(@PathVariable int studentId) {
		return service.fetchById(studentId);
	}

	@PutMapping("/update/{studentId}")
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student,
			@PathVariable int studentId) {
		return service.updateStudent(student, studentId);
	}

	@DeleteMapping("/student/{studentId}")
	public void deleteStudent(@PathVariable int studentId) {
		service.deleteStudent(studentId);
	}
}
