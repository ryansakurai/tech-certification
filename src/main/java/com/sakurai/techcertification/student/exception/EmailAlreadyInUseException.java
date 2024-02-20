package com.sakurai.techcertification.student.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class EmailAlreadyInUseException extends DataIntegrityViolationException {

    public EmailAlreadyInUseException(String email) {
        super( String.format("E-mail '%s' is already in use.", email) );
    }

}
