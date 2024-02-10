package com.sakurai.techcertificationsystem.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakurai.techcertificationsystem.modules.students.dtos.VerifyIfCertifiedDto;
import com.sakurai.techcertificationsystem.modules.students.repositories.CertificationRepository;

@Service
public class VerifyIfCertifiedUseCase {

    @Autowired
    private CertificationRepository certificationRepository;

    public boolean execute(VerifyIfCertifiedDto dto) {
        var certifications = certificationRepository.findByStudentEmailAndTechnology(
            dto.getEmail(),
            dto.getTechnology()
        );
        return !certifications.isEmpty();
    }

}
