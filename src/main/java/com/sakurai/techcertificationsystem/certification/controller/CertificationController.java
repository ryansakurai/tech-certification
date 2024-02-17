package com.sakurai.techcertificationsystem.certification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakurai.techcertificationsystem.certification.dto.SubmitionDto;
import com.sakurai.techcertificationsystem.certification.model.Certification;
import com.sakurai.techcertificationsystem.certification.service.CertificationService;

@RestController
@RequestMapping("/certifications")
public class CertificationController {
    
    @Autowired
    private CertificationService certificationService;

    @GetMapping("/rankings/{quantity}")
    public List<Certification> getRanking(@PathVariable int quantity) {
        return certificationService.getRanking(quantity);
    }

    @PostMapping()
    public Certification submitAnswers(@RequestBody SubmitionDto submition) {
        return this.certificationService.submitAnswers(submition);
    }

}
