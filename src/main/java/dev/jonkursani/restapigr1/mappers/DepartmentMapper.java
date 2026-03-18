package dev.jonkursani.restapigr1.mappers;

import dev.jonkursani.restapigr1.dtos.department.DepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;
import dev.jonkursani.restapigr1.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr1.entities.Department;
import dev.jonkursani.restapigr1.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    // entity -> dto
    DepartmentResponse toDto(Department entity);
    // dto -> entity
    Department toEntity(DepartmentRequest dto);
    void updateEntityFromDto(DepartmentRequest dto, @MappingTarget Department entity);
    Department toEntity(DepartmentResponse dto);

    @Mapping(source = "employees", target = "employeeCount", qualifiedByName = "countPunetoret")
    DepartmentWithEmployeeCount toDepartmentWithEmployeeCount(Department entity);

    @Named("countPunetoret")
    default int countEmployees(Set<Employee> employees) {
        return employees == null ? 0 : employees.size();
    }
}