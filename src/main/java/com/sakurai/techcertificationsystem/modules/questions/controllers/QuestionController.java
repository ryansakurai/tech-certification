package com.sakurai.techcertificationsystem.modules.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakurai.techcertificationsystem.modules.questions.dtos.PublicAlternativeDto;
import com.sakurai.techcertificationsystem.modules.questions.dtos.PublicQuestionDto;
import com.sakurai.techcertificationsystem.modules.questions.entities.Alternative;
import com.sakurai.techcertificationsystem.modules.questions.entities.Question;
import com.sakurai.techcertificationsystem.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    public QuestionRepository repository;

    @GetMapping("/findByTechnology/{technology}")
    public List<PublicQuestionDto> findByTechnology(@PathVariable String technology) {
        var rawQuestions = this.repository.findByTechnology(technology);
        return rawQuestions.stream()
            .map(question -> mapQuestionToDto(question))
            .collect(Collectors.toList());
    }

    // it would probably be better to just filter the results in the query itself, but this is cool
    private static PublicQuestionDto mapQuestionToDto(Question question) {
        var questionDto = PublicQuestionDto.builder()
            .id(question.getId())
            .technology(question.getTechnology())
            .description(question.getDescription())
            .build();

        var alternativeDtos = question.getAlternatives().stream()
            .map(alternative -> mapAlternativeToDto(alternative))
            .collect(Collectors.toList());

        questionDto.setAlternatives(alternativeDtos);
        return questionDto;
    }

    private static PublicAlternativeDto mapAlternativeToDto(Alternative alternative) {
        return PublicAlternativeDto.builder()
            .id(alternative.getId())
            .description(alternative.getDescription())
            .build();
    }

}
