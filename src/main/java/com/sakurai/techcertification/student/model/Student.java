package com.sakurai.techcertification.student.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.sakurai.techcertification.certification.model.Certification;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime creationTime;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;


    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private List<Certification> certifications;

}
