package dev.jonkursani.restapigr1.services;

import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> findAll();
}