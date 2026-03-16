package dev.jonkursani.restapigr1.exceptions.department;

import dev.jonkursani.restapigr1.exceptions.ResourceNotFoundException;

public class DepartmentNotFoundException extends ResourceNotFoundException {
    public DepartmentNotFoundException(int id) {
        super("Department with id " + id + " not found");
    }
}