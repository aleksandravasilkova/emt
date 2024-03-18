package com.example.EMT.service;

import com.example.EMT.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook(Book book);

    void deleteBook(Long id);
    Book updateBook(Long id, Book updatedBook);
    Book markBookAsRented(Long id);
    List<Book> getAllBooks();
    Optional<Book> findById(Long id);
}
