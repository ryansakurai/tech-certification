package com.sakurai.techcertificationsystem.student;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    public Optional<Student> findByEmail(String email);

}
