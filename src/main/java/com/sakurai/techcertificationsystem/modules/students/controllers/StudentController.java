package com.sakurai.techcertificationsystem.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sakurai.techcertificationsystem.modules.students.dtos.SubmitionDto;
import com.sakurai.techcertificationsystem.modules.students.dtos.VerifyIfCertifiedDto;
import com.sakurai.techcertificationsystem.modules.students.entities.Certification;
import com.sakurai.techcertificationsystem.modules.students.useCases.SubmitAnswersUseCase;
import com.sakurai.techcertificationsystem.modules.students.useCases.VerifyIfCertifiedUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfCertifiedUseCase verifyIfCertifiedUseCase;

    @Autowired
    private SubmitAnswersUseCase submitAnswersUseCase;

    @PostMapping("/verifyIfCertified")
    public String verifyIfCertified(@RequestBody VerifyIfCertifiedDto dto) {
        return this.verifyIfCertifiedUseCase.execute(dto) ? "Certified" : "Not Certified";
    }

    @PostMapping("/submitAnswers")
    public Certification submitAnswers(@RequestBody SubmitionDto submition) {
        return this.submitAnswersUseCase.execute(submition);
    }

}
