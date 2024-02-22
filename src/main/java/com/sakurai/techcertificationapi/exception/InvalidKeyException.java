package com.sakurai.techcertificationapi.exception;


public class InvalidKeyException extends Exception {

    public InvalidKeyException(String key, String value) {
        super( String.format("The value of key '%s' is invalid: %s.", key, value) );
    }

}
