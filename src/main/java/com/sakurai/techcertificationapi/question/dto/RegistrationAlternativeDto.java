package com.sakurai.techcertificationapi.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationAlternativeDto {

    private String description;
    private boolean correct;

}
