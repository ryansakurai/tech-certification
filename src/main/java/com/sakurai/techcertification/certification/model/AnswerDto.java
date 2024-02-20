package com.sakurai.techcertification.certification.model;

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
    private boolean isSelected;

}
