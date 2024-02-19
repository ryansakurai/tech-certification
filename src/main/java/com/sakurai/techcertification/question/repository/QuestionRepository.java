package com.sakurai.techcertification.question.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakurai.techcertification.question.model.Question;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    public List<Question> findByTechnology(String technology);

}
