package com.library.controller;

import com.library.model.AvailabilityStatus;
import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

      private final BookService bookService;
    private final ApplicationContext context;

    public BookController(BookService bookService, ApplicationContext context) {
        this.bookService = bookService;
        this.context = context;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
public ResponseEntity<Book> searchBook(@RequestParam(required = false) Long id,
                                       @RequestParam(required = false) String title) {
    return bookService.findByIdOrTitle(id, title)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/shutdown")
    public void shutdown() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                ((org.springframework.context.ConfigurableApplicationContext) context).close();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
    }


    @PostMapping("/updateBook")
public String updateBook(@RequestParam Long id,
                         @RequestParam String title,
                         @RequestParam String author,
                         @RequestParam String genre,
                         @RequestParam String status) {
    Book book = new Book();
    book.setTitle(title);
    book.setAuthor(author);
    book.setGenre(genre);
    book.setStatus(AvailabilityStatus.valueOf(status));
    
    bookService.updateBook(id, book);
    return "redirect:/books"; // Redirect to view all books after update
}

}  