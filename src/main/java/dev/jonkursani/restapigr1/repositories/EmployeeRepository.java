package dev.jonkursani.restapigr1.repositories;

import dev.jonkursani.restapigr1.entities.Department;
import dev.jonkursani.restapigr1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // findAll()
    // findById()
    // save()
    // ...
    List<Employee> findAllByDepartment(Department department);
    boolean existsByEmail(String email);
//    boolean existsEmployeeByEmail(String email);
}