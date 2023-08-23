package com.github.robrousejr.LibraryMS.services;

import com.github.robrousejr.LibraryMS.LibraryMSApplication;
import com.github.robrousejr.LibraryMS.models.Book;
import com.github.robrousejr.LibraryMS.repositories.BookRepository;
import com.github.robrousejr.LibraryMS.repositories.IssuedBookRepository;
import com.github.robrousejr.LibraryMS.util.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LibraryMSApplication.class)
public class BookServiceIntegrationTest {

    // Runs before each test, cleans out all books and issued books
    @BeforeEach
    public void cleanDatabase() {
        issuedBookRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @Test
    public void testGetAllBooks() {

        // Add 3 books
        List<Book> books = TestHelper.buildBookSeries(3);
        bookRepository.saveAll(books);

        // Find all books
        List<Book> actualBooks = new BookService(bookRepository).getAllBooks();

        // Validate that only 3 books exist
        assertThat(actualBooks).hasSize(3);

        // Validate that the books are the ones we added
        List<String> expectedTitles = books.stream().map(Book::getTitle).toList();
        List<String> actualTitles = actualBooks.stream().map(Book::getTitle).toList();
        assertThat(actualTitles).containsExactlyInAnyOrderElementsOf(expectedTitles);
    }
}
