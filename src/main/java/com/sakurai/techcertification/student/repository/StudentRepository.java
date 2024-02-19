package com.sakurai.techcertification.student.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakurai.techcertification.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    public Optional<Student> findByEmail(String email);

}
