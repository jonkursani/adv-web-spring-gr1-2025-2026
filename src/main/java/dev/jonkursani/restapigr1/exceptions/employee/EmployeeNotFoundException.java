package dev.jonkursani.restapigr1.exceptions.employee;

import dev.jonkursani.restapigr1.exceptions.ResourceNotFoundException;

public class EmployeeNotFoundException extends ResourceNotFoundException {
    public EmployeeNotFoundException(int id) {
        super("Employee with id " + id + " not found");
    }
}