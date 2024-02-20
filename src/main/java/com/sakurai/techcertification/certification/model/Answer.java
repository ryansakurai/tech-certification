package com.sakurai.techcertification.certification.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sakurai.techcertification.question.model.Alternative;
import com.sakurai.techcertification.question.model.Question;
import com.sakurai.techcertification.student.model.Student;

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
