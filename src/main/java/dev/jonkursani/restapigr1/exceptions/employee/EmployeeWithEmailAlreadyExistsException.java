package dev.jonkursani.restapigr1.exceptions.employee;

import dev.jonkursani.restapigr1.exceptions.ConflictException;

public class EmployeeWithEmailAlreadyExistsException extends ConflictException {
    public EmployeeWithEmailAlreadyExistsException(String email) {
        super("Employee with email " + email + " already exists");
    }
}