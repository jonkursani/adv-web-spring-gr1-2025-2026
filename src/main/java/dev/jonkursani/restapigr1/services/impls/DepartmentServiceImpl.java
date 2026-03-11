package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;
import dev.jonkursani.restapigr1.entities.Department;
import dev.jonkursani.restapigr1.repositories.DepartmentRepository;
import dev.jonkursani.restapigr1.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    // DI => Dependency Injection
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll()
                .stream()
//                .map(
//                        department -> new DepartmentResponse(
//                                department.getId(), department.getName(), department.getLocation()
//                        )
//                .map(department -> toDto(department))
                .map(this::toDto)
                .toList();
    }

    // konvertimin prej entitetit ne dto
    private DepartmentResponse toDto(Department entity) {
//        return new DepartmentResponse(entity.getId(), entity.getName(), entity.getLocation());
        return DepartmentResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .location(entity.getLocation())
                .build();
    }
}