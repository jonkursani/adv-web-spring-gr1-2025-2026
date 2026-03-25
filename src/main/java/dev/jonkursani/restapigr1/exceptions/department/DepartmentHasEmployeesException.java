package dev.jonkursani.restapigr1.exceptions.department;

import dev.jonkursani.restapigr1.exceptions.ConflictException;

public class DepartmentHasEmployeesException extends ConflictException {
    public DepartmentHasEmployeesException(int id) {
        super("Department with id " + id + " has employees");
    }
}