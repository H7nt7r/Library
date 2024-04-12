package org.example.controllers;
import org.example.models.Book;
import org.example.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // Получение списка всех книг
    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    // Получение определённой книги по её Id
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) {
        return service.getBookById(id);
    }

    // Получение книги по её ISBN
    @GetMapping("/isbn/{isbn}")
    public Book getBookByISBN(@PathVariable String isbn) {
        return service.getBookByISBN(isbn);
    }

    // Добавление новой книги
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    // Изменение информации о существующей книге
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book bookDetails) {
        return service.updateBook(id, bookDetails);
    }

    // Удаление книги
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}

