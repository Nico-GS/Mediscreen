package com.mediscreen.notes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Exception launch if note not found with ID
     * @param message the error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
