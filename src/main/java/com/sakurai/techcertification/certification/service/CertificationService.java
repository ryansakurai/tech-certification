package com.sakurai.techcertification.certification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

@Service
public class CertificationService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CertificationRepository certificationRepository;


    public Certification submitAnswers(SubmitionDto submition) {
        List<Question> questions = questionRepository.findByTechnology(submition.getTechnology());

        correctAnswers(questions, submition.getAnswers());

        Optional<Student> student = this.studentRepository.findByEmail(submition.getEmail());
        UUID studentId;
        if(student.isEmpty()) {
            Student newStudent = Student.builder()
                    .email(submition.getEmail())
                    .build();
            Student insertedStudent = studentRepository.save(newStudent);
            studentId = insertedStudent.getId();
        }
        else {
            studentId = student.get().getId();
        }

        Certification certification = Certification.builder()
            .technology(submition.getTechnology())
            .grade(grade(submition.getAnswers()))
            .studentId(studentId)
            .build();

        Certification insertedCertification = this.certificationRepository.save(certification);

        List<Answer> answers = new ArrayList<>();
        for(AnswerDto dto : submition.getAnswers()) {
            Answer answer = Answer.builder()
                .isCorrect(dto.isCorrect())
                .certificationId(insertedCertification.getId())
                .studentId(studentId)
                .questionId(dto.getQuestionId())
                .alternativeId(dto.getAlternativeId())
                .build();
            answers.add(answer);
        }

        insertedCertification.setAnswers(answers);

        return this.certificationRepository.save(insertedCertification);
    }
    
    private static void correctAnswers(List<Question> dbQuestions, List<AnswerDto> answers) {
        answers.stream().forEach(
            submitedAnswer -> {
                Question dbQuestion = dbQuestions.stream()
                    .filter(q -> q.getId().equals(submitedAnswer.getQuestionId()))
                    .findFirst()
                    .get();
                Alternative correctAlternative = dbQuestion.getAlternatives().stream()
                    .filter(alt -> alt.isCorrect())
                    .findFirst()
                    .get();
                boolean isCorrect = submitedAnswer.getAlternativeId().equals(correctAlternative.getId());
                submitedAnswer.setCorrect(isCorrect);
            }
        );
    }

    private static double grade(List<AnswerDto> answers) {
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
