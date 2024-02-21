package com.sakurai.techcertification.certification.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sakurai.techcertification.certification.model.Certification;
import com.sakurai.techcertification.certification.model.SubmitionDto;
import com.sakurai.techcertification.certification.service.CertificationService;
import com.sakurai.techcertification.exception.ErrorDtoWrapper;
import com.sakurai.techcertification.exception.InvalidKeyException;
import com.sakurai.techcertification.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/certifications")
public class CertificationController {
    
    @Autowired
    private CertificationService certificationService;


    @PostMapping()
    public ResponseEntity<Object> submitAnswers(@RequestBody SubmitionDto submition, UriComponentsBuilder ucb) {
        try {
            Certification certification = this.certificationService.submitAnswers(submition);
            URI certificationUri = ucb
                .path("/certifications/{certificationId}")
                .buildAndExpand(certification.getId())
                .toUri();
            return ResponseEntity.created(certificationUri).build();
        }
        catch(InvalidKeyException e) {
            return ResponseEntity.status(422).body( new ErrorDtoWrapper("invalidKey", e.getMessage()) );
        }
    }


    @GetMapping("/{certificationId}")
    public ResponseEntity<Object> getCertificationById(@PathVariable UUID certificationId) {
        try {
            var certification = certificationService.getById(certificationId);
            return ResponseEntity.ok().body(certification);
        }
        catch(ResourceNotFoundException e) {
            return ResponseEntity.status(404).body( new ErrorDtoWrapper("resourceNotFound", e.getMessage()) );
        }
    }


    @GetMapping("/{technology}/rankings/{quantity}")
    public ResponseEntity<Object> getRankingByTech(@PathVariable String technology,
                                                   @PathVariable int quantity,
                                                   UriComponentsBuilder ucb) {
        try{
            var ranking = certificationService.getRankingByTech(technology, quantity);
            return ResponseEntity.ok().body(ranking);
        }
        catch(ResourceNotFoundException e) {
            return ResponseEntity.status(404).body( new ErrorDtoWrapper("resourceNotFound", e.getMessage()) );
        }
    }

}
