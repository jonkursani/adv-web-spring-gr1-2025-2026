package dev.jonkursani.restapigr1.dtos.employee;

import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private int id;
    private String firstName;
    private String lastName;
    private DepartmentResponse department;
    private LocalDate hireDate;
    private String email;
}