package com.sakurai.techcertificationapi.student.controller;

import java.net.URI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import com.sakurai.techcertificationapi.exception.EmailAlreadyInUseException;
import com.sakurai.techcertificationapi.exception.ErrorDtoWrapper;
import com.sakurai.techcertificationapi.exception.InvalidKeyException;
import com.sakurai.techcertificationapi.exception.ResourceNotFoundException;
import com.sakurai.techcertificationapi.student.model.GetStudentDto;
import com.sakurai.techcertificationapi.student.model.Student;
import com.sakurai.techcertificationapi.student.model.StudentEmailUpdateDto;
import com.sakurai.techcertificationapi.student.model.StudentRegistrationDto;
import com.sakurai.techcertificationapi.student.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;


    @PostMapping()
    public ResponseEntity<Object> registerStudent(@RequestBody StudentRegistrationDto student,
                                                  UriComponentsBuilder ucb) {
        try {
            this.service.registerStudent(student);
            URI uri = ucb
                    .path("/students/{studentId}")
                    .buildAndExpand(student.getEmail())
                    .toUri();
            return ResponseEntity.created(uri).build();
        }
        catch(EmailAlreadyInUseException e) {
            return ResponseEntity.status(409).body( new ErrorDtoWrapper("emailAlreadyInUse", e.getMessage()) );
        }
    }


    @PatchMapping("/{studentEmail}")
    public ResponseEntity<Object> updateStudentEmail(@PathVariable String studentEmail,
                                                     @RequestBody StudentEmailUpdateDto newEmail,
                                                     UriComponentsBuilder ucb) {
        try {
            Student updatedStudent = this.service.updateStudentEmail(studentEmail, newEmail);
            URI uri = ucb
                    .path("/students/{studentEmail}")
                    .buildAndExpand(updatedStudent.getEmail())
                    .toUri();

            return ResponseEntity.ok().location(uri).build();
        }
        catch(InvalidKeyException e) {
            return ResponseEntity.notFound().build();
        }
        catch(EmailAlreadyInUseException e) {
            return ResponseEntity.status(409).body( new ErrorDtoWrapper("emailAlreadyInUse", e.getMessage()) );
        }
    }


    @GetMapping("/{studentEmail}")
    public ResponseEntity<GetStudentDto> getStudentByEmail(@PathVariable String studentEmail,
                                                           UriComponentsBuilder ucb) {
        try {
            var student = service.getStudentByEmail(studentEmail);
            return ResponseEntity.ok().body(student);
        }
        catch(ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
