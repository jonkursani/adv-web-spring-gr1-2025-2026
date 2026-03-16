package dev.jonkursani.restapigr1.repositories;

import dev.jonkursani.restapigr1.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // findAll()
    // findById()
    // save()
    // ...

    // JPQL => Java Persistence Query Language
    // SELECT * FROM department WHERE name = ?1
    // SELECT * FROM department WHERE location = ?1
//    @Query("select d from Department d left join Employee e on d.id = e.department.id")
    @Query("select d from Department d left join fetch d.employees")
    List<Department> findAllWithEmployee();
}