package com.github.robrousejr.LibraryMS.controllers;

import com.github.robrousejr.LibraryMS.models.IssuedBook;
import com.github.robrousejr.LibraryMS.services.IssuedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/issued-books")
public class IssuedBookController {

    private final IssuedBookService issuedBookService;

    @Autowired
    public IssuedBookController(IssuedBookService issuedBookService) {
        this.issuedBookService = issuedBookService;
    }

    // GET /issued-books – List all issued books (admin only)
    @GetMapping
    public List<IssuedBook> getAllIssuedBooks() {
        return issuedBookService.getAllIssuedBooks();
    }

    // GET /issued-books/{id} – Get a specific issued book
    @GetMapping("/{id}")
    public IssuedBook getIssuedBookById(@PathVariable Long id) {
        return issuedBookService.getIssuedBookById(id)
                .orElseThrow(() -> new NoSuchElementException("IssuedBook with ID " + id + " not found"));
    }

    // POST /issued-books – Issue a new book to a user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssuedBook issueNewBook(@RequestBody IssuedBook issuedBook) {
        return issuedBookService.issueNewBook(issuedBook);
    }

    // PUT /issued-books/{id}/return – Return an issued book
    @PutMapping("/{id}/return")
    public IssuedBook returnIssuedBook(@PathVariable Long id) {
        return issuedBookService.returnIssuedBook(id);
    }
}
