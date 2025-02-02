package dev.patika.demo.core.config;

import dev.patika.demo.core.exception.*;
import dev.patika.demo.core.result.Result;
import dev.patika.demo.core.result.ResultData;
import dev.patika.demo.core.ulties.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    //Farklı istisnaları yakalamak ve ona uygun HTTP kodlarıyla anlamlı hata mesajları döndürür.

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VaccineValidityException.class)
    public ResponseEntity<Result> handleVaccineValidityException(VaccineValidityException e) {
        return new ResponseEntity<>(ResultHelper.error(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<Result> handleAppointmentConflictException(AppointmentConflictException e) {
        return new ResponseEntity<>(ResultHelper.error(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordAlreadyExistsException.class)
    public ResponseEntity<Result> handleRecordAlreadyExistsException(RecordAlreadyExistsException e) {
        return new ResponseEntity<>(ResultHelper.error(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Result> handleRecordNotFoundException(RecordNotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}

