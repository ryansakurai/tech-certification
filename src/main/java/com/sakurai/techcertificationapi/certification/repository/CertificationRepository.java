package com.sakurai.techcertificationapi.certification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sakurai.techcertificationapi.certification.model.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, UUID> {

    @Query("SELECT c "+
            "FROM certification c "+
            "WHERE c.technology = :technology "+
            "ORDER BY c.grade DESC "+
            "LIMIT :quantity")
    public List<Certification> findGreatestGradesByTechnology(String technology, int quantity);

}
