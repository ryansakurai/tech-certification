package com.sakurai.techcertification.certification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakurai.techcertification.certification.model.Answer;
import com.sakurai.techcertification.certification.model.SubmitionAnswerDto;
import com.sakurai.techcertification.certification.model.Certification;
import com.sakurai.techcertification.certification.model.AlternativeDto;
import com.sakurai.techcertification.certification.model.AnswerDto;
import com.sakurai.techcertification.certification.model.CertificationDto;
import com.sakurai.techcertification.certification.model.QuestionDto;
import com.sakurai.techcertification.certification.model.RankingCertificationDto;
import com.sakurai.techcertification.certification.model.RankingStudentDto;
import com.sakurai.techcertification.certification.model.SubmitionDto;
import com.sakurai.techcertification.certification.repository.CertificationRepository;
import com.sakurai.techcertification.exception.InvalidKeyException;
import com.sakurai.techcertification.exception.ResourceNotFoundException;
import com.sakurai.techcertification.question.model.Alternative;
import com.sakurai.techcertification.question.model.Question;
import com.sakurai.techcertification.question.repository.QuestionRepository;
import com.sakurai.techcertification.student.model.Student;
import com.sakurai.techcertification.student.repository.StudentRepository;

@Service
public class CertificationService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CertificationRepository certificationRepository;


    public Certification submitAnswers(SubmitionDto submition) throws InvalidKeyException {
        /* TODO: verify if only one is selected */
        /* TODO: verify if student is already certified */

        List<Question> questions = this.questionRepository.findByTechnology(submition.getTechnology().toUpperCase());
        if(questions.isEmpty())
            throw new InvalidKeyException("technology", submition.getTechnology());

        List<Answer> correctedAnswers = correctAnswers(questions, submition.getAnswers());

        Optional<Student> student = this.studentRepository.findByEmail(submition.getEmail());
        if(student.isEmpty())
            throw new InvalidKeyException("email", submition.getEmail());

        Certification certification = Certification.builder()
            .technology(submition.getTechnology().toUpperCase())
            .grade(grade(correctedAnswers))
            .studentId(student.get().getId())
            .answers(correctedAnswers)
            .build();

        return this.certificationRepository.save(certification);
    }
    
    private List<Answer> correctAnswers(List<Question> dbQuestions, List<SubmitionAnswerDto> answers) throws InvalidKeyException {
        var correctedAnswers = new ArrayList<Answer>();

        for(SubmitionAnswerDto answer : answers) {
            Optional<Question> dbQuestion = dbQuestions.stream()
                .filter(dbq -> dbq.getId().equals(answer.getQuestionId()))
                .findAny();
            if(dbQuestion.isEmpty())
                throw new InvalidKeyException("questionId", answer.getQuestionId().toString());

            Optional<Alternative> dbAlternative = dbQuestion.get().getAlternatives().stream()
                .filter(alt -> alt.getId().equals(answer.getAlternativeId()))
                .findAny();
            if(dbAlternative.isEmpty())
                throw new InvalidKeyException("alternativeId", answer.getAlternativeId().toString());

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


    public CertificationDto getById(UUID certificationId) throws ResourceNotFoundException {
        Optional<Certification> entity = certificationRepository.findById(certificationId);
        if(entity.isEmpty())
            throw new ResourceNotFoundException("certification", certificationId.toString());

        return CertificationDto.builder()
            .id(entity.get().getId())
            .technology(entity.get().getTechnology())
            .grade(entity.get().getGrade())
            .timeOfEmition(entity.get().getCreationTime())
            .answers(mapAnswers(entity.get().getAnswers()))
            .build();
    }

    private List<AnswerDto> mapAnswers(List<Answer> answers) {
        return answers.stream()
            .map(answer -> AnswerDto.builder()
                .question(
                    new QuestionDto(answer.getQuestionId(), answer.getQuestion().getDescription())
                ).chosenAlternative(
                    new AlternativeDto(answer.getAlternativeId(), answer.getAlternative().getDescription())
                ).correct(answer.isCorrect())
                .build()
            ).toList();
    }


    public List<RankingCertificationDto> getRankingByTech(String technology, int quantity) throws ResourceNotFoundException {
        List<Certification> certifications = certificationRepository.findGreatestGradesByTechnology(
            technology.toUpperCase(),
            quantity
        );
        if(certifications.isEmpty())
            throw new ResourceNotFoundException("ranking", technology);

        return certifications.stream()
            .map(certification -> RankingCertificationDto.builder()
                .id(certification.getId())
                .student(
                    RankingStudentDto.builder()
                    .name(certification.getStudent().getFullName())
                    .email(certification.getStudent().getEmail())
                    .build()
                ).grade(certification.getGrade())
                .build()
            ).toList();
    }

}
