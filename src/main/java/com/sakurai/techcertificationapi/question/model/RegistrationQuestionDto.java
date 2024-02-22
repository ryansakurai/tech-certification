package com.sakurai.techcertificationapi.question.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationQuestionDto {

    private String technology;
    private String description;
    private List<RegistrationAlternativeDto> alternatives;

}
