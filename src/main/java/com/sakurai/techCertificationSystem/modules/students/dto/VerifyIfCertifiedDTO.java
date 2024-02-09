package com.sakurai.techCertificationSystem.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyIfCertifiedDTO {

    private String email;
    private String technology;

}
