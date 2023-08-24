package com.github.robrousejr.LibraryMS.util;

import com.github.robrousejr.LibraryMS.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TestHelper {

    private static final Random RANDOM = new Random();
    private static final String[] GENRES = {"Fiction", "Non-Fiction", "Science Fiction", "Fantasy", "Mystery"};
    private static final String[] AUTHORS = {"Author A", "Author B", "Author C", "Author D", "Author E"};

    public static List<Book> buildBookSeries(int x) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            Book book = new Book();
            book.setTitle("Title " + RANDOM.nextInt(1000));
            book.setAuthor(AUTHORS[RANDOM.nextInt(AUTHORS.length)]);
            book.setGenre(GENRES[RANDOM.nextInt(GENRES.length)]);
            book.setIsbn("ISBN-" + RANDOM.nextInt(1000000));
            book.setAvailableCopies(RANDOM.nextInt(100));
            books.add(book);
        }
        return books;
    }

    public static Book buildBook() {
        return buildBookSeries(1).get(0);
    }

    // Validates that 2 books are equivalent without comparing their IDs
    public static boolean compareBooks(Book book1, Book book2) {
        return Objects.equals(book1.getTitle(), book2.getTitle()) &&
                Objects.equals(book1.getAuthor(), book2.getAuthor()) &&
                Objects.equals(book1.getIsbn(), book2.getIsbn()) &&
                Objects.equals(book1.getGenre(), book2.getGenre()) &&
                Objects.equals(book1.getAvailableCopies(), book2.getAvailableCopies());
    }

    // Validates that 2 lists of books are equivalent without comparing their IDs
    public static boolean compareBookLists(List<Book> list1, List<Book> list2) {
        if (list1.size() != list2.size()) return false;

        List<Book> unmatchedBooks = new ArrayList<>(list2);
        return list1.stream().allMatch(book1 -> unmatchedBooks.removeIf(book2 -> compareBooks(book1, book2)));
    }


}
