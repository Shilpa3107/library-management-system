package com.library.service;

import com.library.model.AvailabilityStatus;
import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Validate status using EnumSet
        if (!EnumSet.of(AvailabilityStatus.AVAILABLE, AvailabilityStatus.CHECKED_OUT)
                .contains(bookDetails.getStatus())) {
            throw new IllegalArgumentException("Invalid status");
        }

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setStatus(bookDetails.getStatus());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findByIdOrTitle(Long id, String title) {
        if (id != null) {
            return bookRepository.findById(id);
        } else if (title != null) {
            return bookRepository.findByTitle(title);
        }
        return Optional.empty();
    }
}
