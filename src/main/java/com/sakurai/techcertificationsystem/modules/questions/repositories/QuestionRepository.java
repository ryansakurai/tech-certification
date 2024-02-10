package com.sakurai.techcertificationsystem.modules.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakurai.techcertificationsystem.modules.questions.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    public List<Question> findByTechnology(String technology);

}
