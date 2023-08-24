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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testGetAllBooksWithBooks() {

        // Add 3 books
        List<Book> books = TestHelper.buildBookSeries(3);
        bookRepository.saveAll(books);

        // Find all books
        List<Book> actualBooks = new BookService(bookRepository).getAllBooks();

        // Validate that only 3 books exist
        assertThat(actualBooks).hasSize(3);

        // Validate that the books are the ones we added
        assertThat(TestHelper.compareBookLists(books, actualBooks)).isTrue();
    }

    @Test
    public void testGetAllBooksWithoutBooks() {

        // Find all books
        List<Book> actualBooks = new BookService(bookRepository).getAllBooks();

        // Validate that no books exist
        assertThat(actualBooks).isEmpty();
    }

    @Test
    public void testGetBookByIdExists() {

        // Add 3 books
        List<Book> books = TestHelper.buildBookSeries(3);
        bookRepository.saveAll(books);

        // Find the first book
        Book firstBook = bookRepository.findAll().get(0);
        Book actualBook = new BookService(bookRepository).getBookById(firstBook.getId()).get();

        // Validate that the book is the one we added
        assertThat(TestHelper.compareBooks(firstBook, actualBook)).isTrue();
    }

    @Test
    public void testGetBookByIdDoesNotExist() {

        // Find a book that doesn't exist
        Book actualBook = new BookService(bookRepository).getBookById(1L).orElse(null);

        // Validate that no book exists
        assertThat(actualBook).isNull();
    }

    @Test
    public void testSaveBook() {

        // Add a book
        Book book = TestHelper.buildBook();
        Book actualBook = new BookService(bookRepository).saveBook(book);

        // Validate that the book is the one we added
        assertThat(TestHelper.compareBooks(book, actualBook)).isTrue();
    }

    @Test
    public void testDeleteBook() {

        // Add some books
        List<Book> books = TestHelper.buildBookSeries(3);
        bookRepository.saveAll(books);

        // Delete the first book
        Book firstBook = bookRepository.findAll().get(0);
        new BookService(bookRepository).deleteBook(firstBook.getId());

        // Validate that only 2 books exist
        assertThat(bookRepository.findAll()).hasSize(2);

        // Validate that the first book no longer exists
        assertThat(bookRepository.findById(firstBook.getId())).isEmpty();
    }

    @Test
    public void testDeleteBookDoesNotExist() {

        // Delete a book that doesn't exist
        // Should be handled gracefully
        new BookService(bookRepository).deleteBook(1L);

        // Validate that no books exist
        assertThat(bookRepository.findAll()).isEmpty();
    }

    @Test
    public void testUpdateBook() {

        // Add a book
        Book book = TestHelper.buildBook();
        bookRepository.save(book);

        // Update the book
        Book updatedBook = TestHelper.buildBook();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setIsbn("Updated ISBN");
        updatedBook.setGenre("Updated Genre");
        updatedBook.setAvailableCopies(99);
        Book actualBook = new BookService(bookRepository).updateBook(book.getId(), updatedBook);

        // Validate that the book is the one we updated
        assertThat(TestHelper.compareBooks(updatedBook, actualBook)).isTrue();
    }

    @Test
    public void testUpdateBookDoesNotExist() {

        // Update a book that doesn't exist
        // Should be handled gracefully
        Book updatedBook = TestHelper.buildBook();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setIsbn("Updated ISBN");
        updatedBook.setGenre("Updated Genre");
        updatedBook.setAvailableCopies(99);

        assertThrows(NoSuchElementException.class, () -> {
            new BookService(bookRepository).updateBook(1L, updatedBook);
        });

        // Validate that no book exists
        assertThat(bookRepository.findAll()).isEmpty();
    }

    @Test
    public void updateBookTitle() {
        // Add a book
        Book book = TestHelper.buildBook();
        bookRepository.save(book);

        // Update the book
        Book updatedBook = TestHelper.buildBook();
        updatedBook.setTitle("Updated Title");
        Book actualBook = new BookService(bookRepository).updateBook(book.getId(), updatedBook);

        // Validate that the book is the one we updated
        assertThat(TestHelper.compareBooks(updatedBook, actualBook)).isTrue();
    }
}
