package com.sakurai.techcertification.student.controller;

import java.util.UUID;

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

import com.sakurai.techcertification.student.model.GetStudentDto;
import com.sakurai.techcertification.student.model.StudentEmailUpdateDto;
import com.sakurai.techcertification.student.model.StudentRegistrationDto;
import com.sakurai.techcertification.student.service.StudentService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    
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


    @PostMapping()
    public ResponseEntity<Object> registerStudent(@RequestBody StudentRegistrationDto student,
                                                    UriComponentsBuilder ucb) {
        /* TO BE IMPLEMENTED */
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable UUID studentId,
                                                @RequestBody StudentEmailUpdateDto student,
                                                UriComponentsBuilder ucb) {
        /* TO BE IMPLEMENTED */
        return ResponseEntity.ok().build();
    }

}
