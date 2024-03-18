package com.example.EMT.service.ServiceImpl;

import com.example.EMT.model.Book;
import com.example.EMT.repository.BookRepository;
import com.example.EMT.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = this.bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        existingBook.setName(updatedBook.getName());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());

        return bookRepository.save(existingBook);
    }

    @Override
    public Book markBookAsRented(Long id) {
        Book existingBook = this.bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        existingBook.setAvailableCopies(existingBook.getAvailableCopies()-1);


        return bookRepository.save(existingBook);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }
}
