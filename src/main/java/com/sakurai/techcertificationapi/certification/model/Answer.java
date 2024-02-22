package com.sakurai.techcertificationapi.certification.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sakurai.techcertificationapi.question.model.Alternative;
import com.sakurai.techcertificationapi.question.model.Question;

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
    

    @Column(name = "correct")
    private boolean correct;


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
