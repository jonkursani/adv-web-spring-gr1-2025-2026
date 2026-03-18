package dev.jonkursani.restapigr1.mappers;

import dev.jonkursani.restapigr1.dtos.employee.EmployeeRequest;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeResponse;
import dev.jonkursani.restapigr1.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    // Entity -> DTO
    EmployeeResponse toDto(Employee employee);
    Employee toEntity(EmployeeRequest employeeRequest);
    void updateEntityFromDto(EmployeeRequest employeeRequest, @MappingTarget Employee employee);
}