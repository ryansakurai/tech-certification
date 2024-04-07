package com.sakurai.techcertificationapi.certification.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitionAnswerDto {

    private UUID questionId;
    private UUID alternativeId;
    private boolean selected;

}
