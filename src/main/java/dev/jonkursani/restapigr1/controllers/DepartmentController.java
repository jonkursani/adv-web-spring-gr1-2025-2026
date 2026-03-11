package dev.jonkursani.restapigr1.controllers;

import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;
import dev.jonkursani.restapigr1.services.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Department Endpoints", description = "Endpoints related to department")
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
//        List<DepartmentResponse> response = departmentService.findAll();
        var response = departmentService.findAll();
        return ResponseEntity.ok(response);
    }
}