// BookService.java
package org.example.services;

import org.example.models.Book;
import org.example.models.LibraryRecord;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Получение списка всех книг
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Получение определённой книги по её Id
    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));
    }

    // Получение книги по её ISBN
    public Book getBookByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ISBN " + isbn));
    }

    // Добавление новой книги
    public Book createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        // Отправка запроса в LibraryService
        restTemplate.postForObject("http://localhost:8080/api/library/borrow?bookId=" + savedBook.getId(), null, LibraryRecord.class);
        return savedBook;
    }


    // Изменение информации о существующей книге
    public Book updateBook(String id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));

        book.setIsbn(bookDetails.getIsbn());
        book.setTitle(bookDetails.getTitle());
        book.setGenre(bookDetails.getGenre());
        book.setDescription(bookDetails.getDescription());
        book.setAuthor(bookDetails.getAuthor());

        return bookRepository.save(book);
    }

    // Удаление книги
    public void deleteBook(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id " + id));

        bookRepository.delete(book);
    }
}
