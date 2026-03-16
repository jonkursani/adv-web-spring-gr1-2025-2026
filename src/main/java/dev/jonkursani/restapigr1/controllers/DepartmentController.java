package dev.jonkursani.restapigr1.controllers;

import dev.jonkursani.restapigr1.dtos.ErrorResponse;
import dev.jonkursani.restapigr1.dtos.department.DepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;
import dev.jonkursani.restapigr1.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr1.services.DepartmentService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Department Endpoints", description = "Endpoints related to department")
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    // nese perdorni response entity skeni nevoj me perdor @ResponseStatus(HttpStatus.OK)
    // @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Departments retrieved successfully")
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
//        List<DepartmentResponse> response = departmentService.findAll();
        var response = departmentService.findAll();
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Department not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable int id) {
        var department = departmentService.findById(id);
        return ResponseEntity.ok(department);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Department created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) {
        var response = departmentService.create(departmentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department updated successfully"),
            @ApiResponse(responseCode = "404", description = "Department not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable int id,
            @Valid @RequestBody DepartmentRequest departmentRequest
    ) {
        var response = departmentService.update(id, departmentRequest);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Department deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Department not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee-count")
    public ResponseEntity<List<DepartmentWithEmployeeCount>> findAllWithEmployeeCount() {
        var response = departmentService.findAllWithEmployeeCount();
        return ResponseEntity.ok(response);
    }
}