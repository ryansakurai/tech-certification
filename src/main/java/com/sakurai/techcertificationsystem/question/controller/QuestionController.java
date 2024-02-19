package com.sakurai.techcertificationsystem.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakurai.techcertificationsystem.question.model.PublicQuestionDto;
import com.sakurai.techcertificationsystem.question.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{technology}")
    public ResponseEntity<List<PublicQuestionDto>> findByTechnology(@PathVariable String technology) {
        List<PublicQuestionDto> questions = questionService.findByTechnology(technology);
        return ResponseEntity.ok().body(questions);
    }

}
