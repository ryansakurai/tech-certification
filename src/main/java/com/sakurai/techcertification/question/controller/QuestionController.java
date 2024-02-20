package com.sakurai.techcertification.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.sakurai.techcertification.question.model.PublicQuestionDto;
import com.sakurai.techcertification.question.model.QuestionRegistrationDto;
import com.sakurai.techcertification.question.service.QuestionService;


@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/{technology}")
    public ResponseEntity<Object> findByTechnology(@PathVariable String technology,
                                                    UriComponentsBuilder ucb) {
        List<PublicQuestionDto> questions = questionService.findByTechnology(technology);
        return ResponseEntity.ok().body(questions);
    }


    @PostMapping()
    public ResponseEntity<Object> registerQuestion(@RequestBody QuestionRegistrationDto question,
                                                    UriComponentsBuilder ucb) {
        /* TO BE IMPLEMENTED */
        return ResponseEntity.ok().build();
    }

}
