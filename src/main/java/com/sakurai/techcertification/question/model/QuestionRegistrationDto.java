package com.sakurai.techcertification.question.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRegistrationDto {

    private String technology;
    private String description;
    private List<AlternativeRegistrationDto> alternatives;

}
