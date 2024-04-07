package com.sakurai.techcertificationapi.certification.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitionDto {

    private String email;
    private String technology;
    private List<SubmitionAnswerDto> answers;

}
