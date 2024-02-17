package com.sakurai.techcertificationsystem.certification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sakurai.techcertificationsystem.certification.model.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, UUID> {

    @Query("SELECT c " +
            "FROM certification c INNER JOIN c.student s " +
            "WHERE s.email = :email AND c.technology = :technology")
    public List<Certification> findByStudentEmailAndTechnology(String email, String technology);

    @Query("SELECT c " +
            "FROM certification c INNER JOIN c.student s " +
            "ORDER BY c.grade DESC " +
            "LIMIT :quantity")
    public List<Certification> findByGradeDesc(int quantity);

}