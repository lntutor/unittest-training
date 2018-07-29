package com.unittest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unittest.demo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
