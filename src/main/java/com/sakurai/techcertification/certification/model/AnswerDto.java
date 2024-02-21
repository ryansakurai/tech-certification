package com.sakurai.techcertification.certification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {

    private QuestionDto question;
    private AlternativeDto chosenAlternative;
    private boolean correct;
    
}
