package com.sakurai.techcertificationsystem.modules.students.dtos;

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
    private List<AnswerDto> answers;

}
