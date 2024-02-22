package com.sakurai.techcertificationapi.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sakurai.techcertificationapi.certification.model.Certification;
import com.sakurai.techcertificationapi.exception.EmailAlreadyInUseException;
import com.sakurai.techcertificationapi.exception.InvalidKeyException;
import com.sakurai.techcertificationapi.exception.ResourceNotFoundException;
import com.sakurai.techcertificationapi.student.model.GetStudentCertificationDto;
import com.sakurai.techcertificationapi.student.model.GetStudentDto;
import com.sakurai.techcertificationapi.student.model.Student;
import com.sakurai.techcertificationapi.student.model.StudentEmailUpdateDto;
import com.sakurai.techcertificationapi.student.model.StudentRegistrationDto;
import com.sakurai.techcertificationapi.student.repository.StudentRepository;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public Student registerStudent(StudentRegistrationDto studentDto) {
        /* TODO: email validation */
        try {
            var student = Student.builder()
                    .email(studentDto.getEmail())
                    .fullName(studentDto.getFullName())
                    .build();
            return studentRepository.save(student);
        }
        catch(DataIntegrityViolationException e) {
            throw new EmailAlreadyInUseException(studentDto.getEmail());
        }
    }


    public GetStudentDto getStudentByEmail(String email) throws ResourceNotFoundException {
        Optional<Student> student = studentRepository.findByEmail(email);
        if(student.isEmpty())
            throw new ResourceNotFoundException("student", email);

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


    public Student updateStudentEmail(String studentEmail, StudentEmailUpdateDto studentDto) throws InvalidKeyException {
        Optional<Student> student = studentRepository.findByEmail(studentEmail);
        if(student.isEmpty())
            throw new InvalidKeyException("email", studentEmail);

        try {
            student.get().setEmail(studentDto.getEmail());
            return studentRepository.save(student.get());
        }
        catch(DataIntegrityViolationException e) {
            throw new EmailAlreadyInUseException(studentDto.getEmail());
        }
    }

}
