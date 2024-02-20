package com.sakurai.techcertification.student.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentDto {

    private String email;
    private String fullName;
    private List<GetStudentCertificationDto> certifications;

}
