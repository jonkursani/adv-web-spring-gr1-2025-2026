package dev.jonkursani.restapigr1.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // perdoret ne DTO => getter, setter, equals
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    // per texte perdoret @Size
    @Size(min = 1, max = 30, message = "Titulli duhet te jete ne mes 1 dhe {max} karaktere")
    private String title;

    @Size(min = 1, max = 40)
    private String author;

    @Size(min = 1, max = 30)
    private String category;

    // per numra perdoret @Min, @Max
    @Min(value = 1)
    @Max(value = 5)
    private int rating;
}