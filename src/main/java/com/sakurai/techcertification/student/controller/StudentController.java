package com.sakurai.techcertification.student.controller;

import java.net.URI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.sakurai.techcertification.student.exception.EmailAlreadyInUseException;
import com.sakurai.techcertification.student.model.GetStudentDto;
import com.sakurai.techcertification.student.model.Student;
import com.sakurai.techcertification.student.model.StudentEmailUpdateDto;
import com.sakurai.techcertification.student.model.StudentRegistrationDto;
import com.sakurai.techcertification.student.service.StudentService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;


    @PostMapping()
    public ResponseEntity<Object> registerStudent(@RequestBody StudentRegistrationDto student,
                                                    UriComponentsBuilder ucb) {
        /* TODO: improve error responses */
        try {
            this.service.registerStudent(student);
            URI uri = ucb
                        .path("/students/{studentId}")
                        .buildAndExpand(student.getEmail())
                        .toUri();
            return ResponseEntity.created(uri).build();
        }
        catch(EmailAlreadyInUseException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }


    @PatchMapping("/{studentEmail}")
    public ResponseEntity<Object> updateStudent(@PathVariable String studentEmail,
                                                @RequestBody StudentEmailUpdateDto newEmail,
                                                UriComponentsBuilder ucb) {
        try {
            Student updatedStudent = this.service.updateStudent(studentEmail, newEmail);
            URI uri = ucb
                .path("/students/{studentEmail}")
                .buildAndExpand(updatedStudent.getEmail())
                .toUri();

            return ResponseEntity.ok().location(uri).build();
        }
        catch(EmailAlreadyInUseException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
        catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{studentEmail}")
    public ResponseEntity<GetStudentDto> getStudentByEmail(@PathVariable String studentEmail,
                                                            UriComponentsBuilder ucb) {
        try {
            var student = service.getStudentByEmail(studentEmail);
            return ResponseEntity.ok().body(student);
        }
        catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
