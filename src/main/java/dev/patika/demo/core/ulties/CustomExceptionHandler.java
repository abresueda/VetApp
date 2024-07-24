package dev.patika.demo.core.ulties;

import dev.patika.demo.core.exception.VaccineValidityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(VaccineValidityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleVaccineValidityException(VaccineValidityException e) {
        return e.getMessage();
    }
}
