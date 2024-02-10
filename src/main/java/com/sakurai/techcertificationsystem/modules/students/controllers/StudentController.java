package com.sakurai.techcertificationsystem.modules.students.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakurai.techcertificationsystem.modules.students.dtos.VerifyIfCertifiedDTO;
import com.sakurai.techcertificationsystem.modules.students.useCases.VerifyIfCertifiedUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfCertifiedUseCase useCase;

    @PostMapping("/verifyIfCertified")
    public String verifyIfCertified(@RequestBody VerifyIfCertifiedDTO dto) {
        return this.useCase.execute(dto) ? "Certified" : "Not Certified";
    }

}
