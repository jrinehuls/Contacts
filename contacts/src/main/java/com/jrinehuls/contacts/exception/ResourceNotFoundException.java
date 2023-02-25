package com.jrinehuls.contacts.exception;

public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String id) {

        super(String.format("Contact with id: '%s' not found.", id));

    }
}
