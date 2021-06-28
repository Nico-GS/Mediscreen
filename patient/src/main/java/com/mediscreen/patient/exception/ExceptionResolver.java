package com.mediscreen.patient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    /*
    Controller exception bad request
     */
    @ExceptionHandler
    public ResponseEntity handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ExceptionResponse hResourceNotFoundException (ResourceNotFoundException resourceNotFoundException,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) {
        response.setStatus(404);
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 404, "Patient not found with ID",
                request.getRequestURI());
        LOGGER.info("Error : " + resourceNotFoundException + " " + response);
        return exceptionResponse;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ExceptionResponse hIllegalArgumentException(IllegalArgumentException illegalArgumentException,
                                                       HttpServletRequest request,
                                                       HttpServletResponse responseCode) {
        responseCode.setStatus(400);
        ExceptionResponse response = new ExceptionResponse(new Date(), 400,
                illegalArgumentException.getLocalizedMessage(),
                request.getRequestURI());
        LOGGER.info("Error : " + illegalArgumentException + " " + response);
        return response;
    }

}
