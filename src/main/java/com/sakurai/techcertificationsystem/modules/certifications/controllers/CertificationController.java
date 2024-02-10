package com.sakurai.techcertificationsystem.modules.certifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakurai.techcertificationsystem.modules.certifications.useCases.RankingUseCase;
import com.sakurai.techcertificationsystem.modules.students.entities.Certification;

@RestController
@RequestMapping("/certifications")
public class CertificationController {
    
    @Autowired
    private RankingUseCase rankingUseCase;

    @GetMapping("/getRanking/{quantity}")
    public List<Certification> getRanking(@PathVariable int quantity) {
        return rankingUseCase.execute(quantity);
    }

}
