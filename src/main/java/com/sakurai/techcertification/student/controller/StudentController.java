package com.sakurai.techcertification.student.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;

import com.sakurai.techcertification.student.model.StudentEmailUpdateDto;
import com.sakurai.techcertification.student.model.StudentRegistrationDto;


@RestController
@RequestMapping("/students")
public class StudentController {

    
    @GetMapping("/{studentId}")
    public ResponseEntity<Object> getStudentById(@PathVariable UUID studentId,
                                                    UriComponentsBuilder ucb) {
        /* TO BE IMPLEMENTED */
        return ResponseEntity.ok().build();
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
