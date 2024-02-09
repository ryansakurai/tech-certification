package com.sakurai.techCertificationSystem.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.sakurai.techCertificationSystem.modules.students.dto.VerifyIfCertifiedDTO;

@Service
public class VerifyIfCertifiedUseCase {

    public boolean execute(VerifyIfCertifiedDTO dto) {
        return dto.getEmail().equals("ryansakurai@gmail.com") && dto.getTechnology().equals("JAVA");
    }

}
