package com.sakurai.techcertificationsystem.certification.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sakurai.techcertificationsystem.question.model.Alternative;
import com.sakurai.techcertificationsystem.question.model.Question;
import com.sakurai.techcertificationsystem.student.model.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime creationTime;


    @Column(name = "certification_id")
    private UUID certificationId;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private Certification certification;


    @Column(name = "student_id")
    private UUID studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @JsonBackReference
    private Student student;


    @Column(name = "question_id")
    private UUID questionId;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    @JsonBackReference
    private Question question;


    @Column(name = "alternative_id")
    private UUID alternativeId;

    @OneToOne
    @JoinColumn(name = "alternative_id", insertable = false, updatable = false)
    @JsonBackReference
    private Alternative alternative;

}
