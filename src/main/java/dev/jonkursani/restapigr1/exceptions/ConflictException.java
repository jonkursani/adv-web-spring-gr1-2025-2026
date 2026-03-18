package dev.jonkursani.restapigr1.exceptions;

public class ConflictException extends IllegalArgumentException {
    public ConflictException(String message) {
        super(message);
    }
}