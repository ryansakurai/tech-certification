package com.sakurai.techcertification.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakurai.techcertification.certification.model.Certification;
import com.sakurai.techcertification.student.model.GetStudentCertificationDto;
import com.sakurai.techcertification.student.model.GetStudentDto;
import com.sakurai.techcertification.student.model.Student;
import com.sakurai.techcertification.student.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public GetStudentDto getStudentByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        if(student.isEmpty())
            throw new EntityNotFoundException();

        return GetStudentDto.builder()
                            .email(student.get().getEmail())
                            .fullName(student.get().getFullName())
                            .certifications(parseCertifications(student.get().getCertifications()))
                            .build();
    }

    private static List<GetStudentCertificationDto> parseCertifications(List<Certification> certifications) {
        var output = new ArrayList<GetStudentCertificationDto>();
        for(Certification c: certifications) {
            var dto = GetStudentCertificationDto.builder()
                                                .id(c.getId())
                                                .technology(c.getTechnology())
                                                .build();
            output.add(dto);
        }
        return output;
    }

}
