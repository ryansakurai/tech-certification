package com.sakurai.techcertificationsystem.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakurai.techcertificationsystem.modules.questions.entities.Alternative;
import com.sakurai.techcertificationsystem.modules.questions.entities.Question;
import com.sakurai.techcertificationsystem.modules.questions.repositories.QuestionRepository;
import com.sakurai.techcertificationsystem.modules.students.dtos.AnswerDto;
import com.sakurai.techcertificationsystem.modules.students.dtos.SubmitionDto;
import com.sakurai.techcertificationsystem.modules.students.entities.Answer;
import com.sakurai.techcertificationsystem.modules.students.entities.Certification;
import com.sakurai.techcertificationsystem.modules.students.entities.Student;
import com.sakurai.techcertificationsystem.modules.students.repositories.CertificationRepository;
import com.sakurai.techcertificationsystem.modules.students.repositories.StudentRepository;

@Service
public class SubmitAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    public Certification execute(SubmitionDto submition) {
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

}
