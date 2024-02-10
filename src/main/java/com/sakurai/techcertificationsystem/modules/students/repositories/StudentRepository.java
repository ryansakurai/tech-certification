package com.sakurai.techcertificationsystem.modules.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakurai.techcertificationsystem.modules.students.entities.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    public Optional<Student> findByEmail(String email);

}
