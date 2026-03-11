package dev.jonkursani.restapigr1.repositories;

import dev.jonkursani.restapigr1.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // findAll()
    // findById()
    // save()
    // ...
}