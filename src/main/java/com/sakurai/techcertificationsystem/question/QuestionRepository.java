package com.sakurai.techcertificationsystem.question;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    public List<Question> findByTechnology(String technology);

}
