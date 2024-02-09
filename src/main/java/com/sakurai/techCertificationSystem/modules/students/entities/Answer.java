package com.sakurai.techCertificationSystem.modules.students.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    private UUID id;
    private UUID certificationId;
    private UUID studentId;
    private UUID questionId;
    private UUID alternativeId;
    private boolean isCorrect;

}
