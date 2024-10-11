package com.example.SpringBootAPICrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SpringBootAPICrud.dto.Student;
import com.example.SpringBootAPICrud.exception.DataExistsException;
import com.example.SpringBootAPICrud.exception.DataNotFoundException;
import com.example.SpringBootAPICrud.helper.ResponseStructure;
import com.example.SpringBootAPICrud.repository.StudentRepository;

@Service
public class StudentApiService {

	@Autowired
	StudentRepository repository;

	@Autowired
	ResponseStructure<Student> structure1;

	@Autowired
	ResponseStructure<List<Student>> structure2;

	public ResponseEntity<ResponseStructure<Student>> addStudent(Student student) {
		repository.save(student);
		structure1.setMessage("Data Saved Success");
		structure1.setData(student);
		return new ResponseEntity<ResponseStructure<Student>>(structure1, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Student>> fetchById(int studentId) {
		Student student = repository.findById(studentId)
				.orElseThrow(() -> new DataNotFoundException("Data Not Found with Id: " + studentId));
		structure1.setMessage("Data Found");
		structure1.setData(student);
		return new ResponseEntity<ResponseStructure<Student>>(structure1, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Student>>> fetchAll() {
		List<Student> list = repository.findAll();
		if (list.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			structure2.setMessage("Data Found");
			structure2.setData(list);
			return new ResponseEntity<ResponseStructure<List<Student>>>(structure2, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student requestStudent, int studentId) {
		Student existingStudent = repository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));

		existingStudent.setStudentName(requestStudent.getStudentName());
		existingStudent.setStudentEmail(requestStudent.getStudentEmail());
		existingStudent.setMobile(requestStudent.getMobile());
		existingStudent.setCgpa(requestStudent.getCgpa());
		existingStudent.setGrade(requestStudent.getGrade());

		repository.save(existingStudent);
		structure1.setMessage("Data Updated Success");
		structure1.setData(requestStudent);

		return new ResponseEntity<ResponseStructure<Student>>(structure1, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Student>> deleteStudent(int id) {
		Student employee = repository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Data Not Found with Id :" + id));

		repository.deleteById(id);
		structure1.setMessage("Data Removed");
		structure1.setData(employee);
		return new ResponseEntity<ResponseStructure<Student>>(structure1, HttpStatus.OK);
	}

}
