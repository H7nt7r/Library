package org.example.services;

import org.example.models.Book;
import org.example.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // Получение списка всех книг
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // Получение определённой книги по её Id
    public Book getBookById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));
    }

    // Получение книги по её ISBN
    public Book getBookByISBN(String isbn) {
        return repository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ISBN " + isbn));
    }

    // Добавление новой книги
    public Book createBook(Book book) {
        return repository.save(book);
    }

    // Изменение информации о существующей книге
    public Book updateBook(String id, Book bookDetails) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));

        book.setIsbn(bookDetails.getIsbn());
        book.setTitle(bookDetails.getTitle());
        book.setGenre(bookDetails.getGenre());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(bookDetails.getAuthor());

        return repository.save(book);
    }

    // Удаление книги
    public void deleteBook(String id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));

        repository.delete(book);
    }
}


