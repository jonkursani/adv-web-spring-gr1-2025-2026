package dev.jonkursani.restapigr1.exceptions.book;

import dev.jonkursani.restapigr1.exceptions.ResourceNotFoundException;

public class BookNotFoundException extends ResourceNotFoundException {
    public BookNotFoundException(long id) {
        super("Book with id " + id + " not found");
    }
}