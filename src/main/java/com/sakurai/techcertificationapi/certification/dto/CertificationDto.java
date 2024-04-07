package com.sakurai.techcertificationapi.certification.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificationDto {

    private UUID id;
    private String technology;
    private double grade;
    private LocalDateTime timeOfEmition;
    private List<AnswerDto> answers;
    
}
