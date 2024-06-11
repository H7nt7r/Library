package org.example.repositories;

import org.example.models.LibraryRecord;
import org.example.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRecordRepository extends JpaRepository<LibraryRecord, Long> {

    @Query("SELECT b FROM Book b LEFT JOIN LibraryRecord lr ON b.id = lr.bookId WHERE lr.returnedAt IS NOT NULL OR lr.bookId IS NULL ORDER BY b.id")
    List<Book> findAvailableBooks();

    List<LibraryRecord> findByReturnedAtIsNull();
}