package com.github.robrousejr.LibraryMS.util;

import com.github.robrousejr.LibraryMS.models.Book;

import java.util.ArrayList;
import java.util.List;
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

}
