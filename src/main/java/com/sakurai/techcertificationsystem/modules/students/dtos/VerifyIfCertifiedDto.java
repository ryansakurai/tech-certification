package com.sakurai.techcertificationsystem.modules.students.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyIfCertifiedDto {

    private String email;
    private String technology;

}
