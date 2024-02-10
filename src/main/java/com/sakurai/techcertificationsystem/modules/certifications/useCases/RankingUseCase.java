package com.sakurai.techcertificationsystem.modules.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakurai.techcertificationsystem.modules.students.entities.Certification;
import com.sakurai.techcertificationsystem.modules.students.repositories.CertificationRepository;

@Service
public class RankingUseCase {

    @Autowired
    private CertificationRepository certificationRepository;

    public List<Certification> execute(int quantity) {
        return certificationRepository.findByGradeDesc(quantity);
    }
    
}
