package com.sakurai.techcertification.exception;

import lombok.Data;

@Data
public class ErrorDtoWrapper {

    private ErrorDto error;

    public ErrorDtoWrapper(String code, String details) {
        this.error = new ErrorDto(code, details);
    }

}
