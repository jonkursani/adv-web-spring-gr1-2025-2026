package dev.jonkursani.restapigr1.controllers;

import dev.jonkursani.restapigr1.dtos.ErrorResponse;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeRequest;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeResponse;
import dev.jonkursani.restapigr1.services.EmployeeService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Endpoints", description = "Endpoints for managing employees")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PreAuthorize("hasAuthority('employee:read')")
    @GetMapping // ?departmentId=1
    public ResponseEntity<List<EmployeeResponse>> getEmployees(@RequestParam(required = false) Integer departmentId) {
//        var response = employeeService.findAll();
//        return ResponseEntity.ok(response);
        return ResponseEntity.ok(employeeService.findAll(departmentId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Department not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Employee with email already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest employeeRequest) {
        var response = employeeService.create(employeeRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Department not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Employee with email already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable int id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        var response = employeeService.update(id, employeeRequest);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('employee:write')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}