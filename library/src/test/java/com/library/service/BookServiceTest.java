package com.library.service;

import com.library.model.AvailabilityStatus;
import com.library.model.Book;
import com.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testAddBook() {
        Book book = new Book(1L, "Test Title", "Test Author", "Test Genre", AvailabilityStatus.AVAILABLE);
        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.addBook(book);

        assertEquals("Test Title", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }
}
