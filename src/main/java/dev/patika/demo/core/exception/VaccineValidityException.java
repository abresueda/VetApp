package dev.patika.demo.core.exception;

public class VaccineValidityException extends RuntimeException{
    public VaccineValidityException(String message) {
        super(message);
    }
}
