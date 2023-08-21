package com.github.robrousejr.LibraryMS.services;

import com.github.robrousejr.LibraryMS.models.IssuedBook;
import com.github.robrousejr.LibraryMS.repositories.IssuedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IssuedBookService {
    private final IssuedBookRepository issuedBookRepository;

    @Autowired
    public IssuedBookService(IssuedBookRepository issuedBookRepository) {
        this.issuedBookRepository = issuedBookRepository;
    }

    // GET /issued-books – List all issued books (admin only)
    public List<IssuedBook> getAllIssuedBooks() {
        return issuedBookRepository.findAll();
    }

    // GET /issued-books/{id} – Get a specific issued book
    public Optional<IssuedBook> getIssuedBookById(Long id) {
        return issuedBookRepository.findById(id);
    }

    // POST /issued-books – Issue a new book to a user
    public IssuedBook issueNewBook(IssuedBook issuedBook) {
        return issuedBookRepository.save(issuedBook);
    }

    // PUT /issued-books/{id}/return – Return an issued book
    public IssuedBook returnIssuedBook(Long id) {
        return issuedBookRepository.findById(id)
                .map(issuedBook -> {
                    issuedBook.setReturnDate(new Date()); // Set the return date to now
                    return issuedBookRepository.save(issuedBook);
                })
                .orElseThrow(() -> new IllegalArgumentException("IssuedBook with ID " + id + " not found"));
    }
}
