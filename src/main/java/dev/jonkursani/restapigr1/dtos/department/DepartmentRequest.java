package dev.jonkursani.restapigr1.dtos.department;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    @Size(min = 2, max = 100, message = "Emri duhet te jete ne mes te {min} dhe {max} karaktere")
    private String name;

    @Size(max = 100, message = "Lokacioni duhet te kete me pak ose {max} karaktere")
    private String location;
}