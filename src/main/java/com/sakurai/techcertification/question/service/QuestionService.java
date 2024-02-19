package com.sakurai.techcertification.question.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakurai.techcertification.question.model.Alternative;
import com.sakurai.techcertification.question.model.PublicAlternativeDto;
import com.sakurai.techcertification.question.model.PublicQuestionDto;
import com.sakurai.techcertification.question.model.Question;
import com.sakurai.techcertification.question.repository.QuestionRepository;

@Service
public class QuestionService {
    
    @Autowired
    public QuestionRepository questionRepository;

    public List<PublicQuestionDto> findByTechnology(String technology) {
        List<Question> rawQuestions = this.questionRepository.findByTechnology(technology);
        return rawQuestions.stream()
            .map(question -> mapQuestionToDto(question))
            .collect(Collectors.toList());
    }

    // it would probably be better to just filter the results in the query itself, but this is cool
    private static PublicQuestionDto mapQuestionToDto(Question question) {
        PublicQuestionDto questionDto = PublicQuestionDto.builder()
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
