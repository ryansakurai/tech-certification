package com.sakurai.techcertification.exception;


public class InvalidKeyException extends Exception {

    public InvalidKeyException(String key, String value) {
        super( String.format("The value of key '%s' is invalid: %s.", key, value) );
    }

}
