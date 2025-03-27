package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.library.model.Book;
import com.library.service.BookService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/books")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list"; 
    }

    @GetMapping("/addBook")
    public String addBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "books/add"; 
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books"; 
    }
}
