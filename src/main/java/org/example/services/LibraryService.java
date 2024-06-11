package org.example.services;

import org.example.models.Book;
import org.example.models.LibraryRecord;
import org.example.repositories.LibraryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRecordRepository libraryRecordRepository;

    public List<Book> getAvailableBooks() {
        return libraryRecordRepository.findAvailableBooks();
    }

    public List<LibraryRecord> getBorrowedBooks() {
        return libraryRecordRepository.findByReturnedAtIsNull();
    }

    public LibraryRecord borrowBook(Long bookId) {
        LibraryRecord record = new LibraryRecord();
        record.setBookId(bookId);
        record.setBorrowedAt(LocalDateTime.now());
        record.setDueAt(LocalDateTime.now().plus(14, ChronoUnit.DAYS));
        return libraryRecordRepository.save(record);
    }

    public LibraryRecord returnBook(Long recordId) {
        return libraryRecordRepository.findById(recordId).map(record -> {
            record.setReturnedAt(LocalDateTime.now());
            return libraryRecordRepository.save(record);
        }).orElseThrow(() -> new RuntimeException("Record not found"));
    }
}