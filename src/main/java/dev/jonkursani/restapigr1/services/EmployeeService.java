package dev.jonkursani.restapigr1.services;

import dev.jonkursani.restapigr1.dtos.employee.EmployeeRequest;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> findAll(Integer departmentId);
    EmployeeResponse findById(int id);
    EmployeeResponse create(EmployeeRequest employeeRequest);
    EmployeeResponse update(int id, EmployeeRequest employeeRequest);
}