package dev.jonkursani.restapigr1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // perdoret ne DTO => getter, setter, equals
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private String category;
    private int rating;
}