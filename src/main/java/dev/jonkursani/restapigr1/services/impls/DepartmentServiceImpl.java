package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.dtos.department.DepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;
import dev.jonkursani.restapigr1.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr1.entities.Department;
import dev.jonkursani.restapigr1.exceptions.department.DepartmentNotFoundException;
import dev.jonkursani.restapigr1.mappers.DepartmentMapper;
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
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll()
                .stream()
//                .map(
//                        department -> new DepartmentResponse(
//                                department.getId(), department.getName(), department.getLocation()
//                        )
//                .map(department -> toDto(department))
//                .map(this::toDto)
                .map(departmentMapper::toDto)
                .toList();
    }

    @Override
    public DepartmentResponse findById(int id) {
        return departmentRepository.findById(id)
//                .map(this::toDto)
//                .map(department -> departmentMapper.toDto(department))
                .map(departmentMapper::toDto)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public DepartmentResponse create(DepartmentRequest departmentRequest) {
        var department = departmentMapper.toEntity(departmentRequest);
        var createdDepartment = departmentRepository.save(department);
        return departmentMapper.toDto(createdDepartment);
    }

    @Override
    public DepartmentResponse update(int id, DepartmentRequest departmentRequest) {
        var departmentFromDb = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        departmentMapper.updateEntityFromDto(departmentRequest, departmentFromDb);
        var updatedDepartment = departmentRepository.save(departmentFromDb);
        return departmentMapper.toDto(updatedDepartment);
    }

    @Override
    public void delete(int id) {
        var departmentFromDb = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

//        departmentRepository.delete(departmentFromDb);
        departmentRepository.deleteById(departmentFromDb.getId());
    }

    @Override
    public List<DepartmentWithEmployeeCount> findAllWithEmployeeCount() {
        return departmentRepository.findAllWithEmployee()
                .stream()
                .map(departmentMapper::toDepartmentWithEmployeeCount)
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