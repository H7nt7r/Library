package org.example.controllers;

import org.example.models.Book;
import org.example.models.LibraryRecord;
import org.example.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return libraryService.getAvailableBooks();
    }

    @GetMapping("/borrowed")
    public List<LibraryRecord> getBorrowedBooks() {
        return libraryService.getBorrowedBooks();
    }

    @PostMapping("/borrow")
    public LibraryRecord borrowBook(@RequestParam Long bookId) {
        return libraryService.borrowBook(bookId);
    }

    @PostMapping("/return/{id}")
    public LibraryRecord returnBook(@PathVariable Long id) {
        return libraryService.returnBook(id);
    }
}