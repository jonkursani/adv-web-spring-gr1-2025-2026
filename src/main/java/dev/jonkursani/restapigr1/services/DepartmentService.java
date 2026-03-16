package dev.jonkursani.restapigr1.services;

import dev.jonkursani.restapigr1.dtos.department.DepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;
import dev.jonkursani.restapigr1.dtos.department.DepartmentWithEmployeeCount;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> findAll();
    DepartmentResponse findById(int id);
    DepartmentResponse create(DepartmentRequest departmentRequest);
    DepartmentResponse update(int id, DepartmentRequest departmentRequest);
    void delete(int id);
    List<DepartmentWithEmployeeCount> findAllWithEmployeeCount();
}