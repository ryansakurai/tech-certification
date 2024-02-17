package com.sakurai.techcertificationsystem.certification.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private UUID questionId;
    private UUID alternativeId;
    private boolean isCorrect;

}
