package com.sakurai.techcertification.exception;


public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String resource, String key) {
        super( String.format("Resource '%s' with key '%s' not found.", resource, key) );
    }
    
}
