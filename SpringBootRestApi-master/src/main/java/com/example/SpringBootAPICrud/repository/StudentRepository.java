package com.example.SpringBootAPICrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootAPICrud.dto.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	boolean existsByMobile(long mobile);

}
