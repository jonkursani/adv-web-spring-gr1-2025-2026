package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.dtos.department.DepartmentResponse;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeRequest;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeResponse;
import dev.jonkursani.restapigr1.entities.Department;
import dev.jonkursani.restapigr1.exceptions.employee.EmployeeNotFoundException;
import dev.jonkursani.restapigr1.exceptions.employee.EmployeeWithEmailAlreadyExistsException;
import dev.jonkursani.restapigr1.mappers.DepartmentMapper;
import dev.jonkursani.restapigr1.mappers.EmployeeMapper;
import dev.jonkursani.restapigr1.repositories.EmployeeRepository;
import dev.jonkursani.restapigr1.services.DepartmentService;
import dev.jonkursani.restapigr1.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<EmployeeResponse> findAll(Integer departmentId) {
        if (departmentId != null) {
            // kontrollo a ekziston departmenti
            DepartmentResponse departmentResponse = departmentService.findById(departmentId);
            // kthimi prej DepartmentResponse ne Department => dto -> entity
            Department department = departmentMapper.toEntity(departmentResponse);

            return employeeRepository.findAllByDepartment(department)
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        } else {
            return employeeRepository.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        }
    }

    @Override
    public EmployeeResponse findById(int id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public EmployeeResponse create(EmployeeRequest employeeRequest) {
        // kontrollo nese email ekziston
        if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            throw new EmployeeWithEmailAlreadyExistsException(employeeRequest.getEmail());
        }

        // kontrollo nese departamenti ekziston
        var departmentResponse = departmentService.findById(employeeRequest.getDepartmentId());
        var department = departmentMapper.toEntity(departmentResponse);

        // employee entity -> dto
        var employee = employeeMapper.toEntity(employeeRequest);
        // set department
        employee.setDepartment(department);

        var createdEmployee = employeeRepository.save(employee);

        return employeeMapper.toDto(createdEmployee);
    }

    @Override
    public EmployeeResponse update(int id, EmployeeRequest employeeRequest) {
        // kontrollo nese ekziston punetori qe don me bo update
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));


        // po don me bo update email pra email sosht e njejt ne db dhe dto
//        if (!employee.getEmail().equalsIgnoreCase(employeeRequest.getEmail())) {
//            // kontrollo a ekziston
//            if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
//                throw new EmployeeWithEmailAlreadyExistsException(employeeRequest.getEmail());
//            }
//        }

        if (!employee.getEmail().equalsIgnoreCase(employeeRequest.getEmail()) &&
                employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            throw new EmployeeWithEmailAlreadyExistsException(employeeRequest.getEmail());
        }

        // kontrollo nese departamenti ekziston
        var departmentResponse = departmentService.findById(employeeRequest.getDepartmentId());
        var department = departmentMapper.toEntity(departmentResponse);

        employeeMapper.updateEntityFromDto(employeeRequest, employee);
        employee.setDepartment(department);

        var updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDto(updatedEmployee);
    }
}