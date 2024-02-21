package com.sakurai.techcertification.certification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakurai.techcertification.certification.model.Answer;
import com.sakurai.techcertification.certification.model.AnswerDto;
import com.sakurai.techcertification.certification.model.Certification;
import com.sakurai.techcertification.certification.model.SubmitionDto;
import com.sakurai.techcertification.certification.repository.CertificationRepository;
import com.sakurai.techcertification.question.model.Alternative;
import com.sakurai.techcertification.question.model.Question;
import com.sakurai.techcertification.question.repository.QuestionRepository;
import com.sakurai.techcertification.student.model.Student;
import com.sakurai.techcertification.student.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CertificationService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CertificationRepository certificationRepository;


    public Certification submitAnswers(SubmitionDto submition) throws EntityNotFoundException {
        /* TODO: verify if only one is selected */

        List<Question> questions = this.questionRepository.findByTechnology(submition.getTechnology().toUpperCase());
        if(questions.isEmpty())
            throw new EntityNotFoundException("No questions available for this technology.");

        List<Answer> correctedAnswers = correctAnswers(questions, submition.getAnswers());

        Optional<Student> student = this.studentRepository.findByEmail(submition.getEmail());
        if(student.isEmpty())
            throw new EntityNotFoundException("Student not found in database: " + submition.getEmail());

        Certification certification = Certification.builder()
            .technology(submition.getTechnology().toUpperCase())
            .grade(grade(correctedAnswers))
            .studentId(student.get().getId())
            .answers(correctedAnswers)
            .build();

        return this.certificationRepository.save(certification);
    }
    
    private List<Answer> correctAnswers(List<Question> dbQuestions, List<AnswerDto> answers) throws EntityNotFoundException {
        var correctedAnswers = new ArrayList<Answer>();

        for(AnswerDto answer : answers) {
            Optional<Question> dbQuestion = dbQuestions.stream()
                .filter(dbq -> dbq.getId().equals(answer.getQuestionId()))
                .findAny();
            if(dbQuestion.isEmpty())
                throw new EntityNotFoundException("Question not found in database: " + answer.getQuestionId());

            Optional<Alternative> dbAlternative = dbQuestion.get().getAlternatives().stream()
                .filter(alt -> alt.getId().equals(answer.getAlternativeId()))
                .findAny();
            if(dbAlternative.isEmpty())
                throw new EntityNotFoundException("Alternative not found in database: " + answer.getAlternativeId());

            Answer correctedAnswer = Answer.builder()
                .questionId(dbQuestion.get().getId())
                .alternativeId(dbAlternative.get().getId())
                .correct(dbAlternative.get().isCorrect())
                .build();
            correctedAnswers.add(correctedAnswer);
        }

        return correctedAnswers;
    }

    private static double grade(List<Answer> answers) {
        int qtCorrectAnswers = (int) answers.stream()
            .filter(a -> a.isCorrect())
            .count();

        double rawGrade = ((double) qtCorrectAnswers / answers.size()) * 10;
        return roundToTwoDecimals(rawGrade);
    }

    private static double roundToTwoDecimals(double value) {
        return Math.round(value * 100) / 100.0;
    }


    public List<Certification> getRanking(int quantity) {
        return certificationRepository.findByGradeDesc(quantity);
    }

}
