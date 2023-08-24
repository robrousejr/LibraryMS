package com.github.robrousejr.LibraryMS.util;

import com.github.robrousejr.LibraryMS.models.Book;
import com.github.robrousejr.LibraryMS.models.Role;
import com.github.robrousejr.LibraryMS.models.User;

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

    public static List<User> buildUserSeries(int x) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            User user = new User();
            user.setName("Name " + RANDOM.nextInt(1000));
            user.setEmail("Email " + RANDOM.nextInt(1000));
            user.setPassword("Password " + RANDOM.nextInt(1000));
            user.setRole(Role.USER);
            users.add(user);
        }
        return users;
    }

    public static Book buildBook() {
        return buildBookSeries(1).get(0);
    }

    public static User buildUser() {
        return buildUserSeries(1).get(0);
    }

    // Validates that 2 books are equivalent without comparing their IDs
    public static boolean compareBooks(Book book1, Book book2) {
        return Objects.equals(book1.getTitle(), book2.getTitle()) &&
                Objects.equals(book1.getAuthor(), book2.getAuthor()) &&
                Objects.equals(book1.getIsbn(), book2.getIsbn()) &&
                Objects.equals(book1.getGenre(), book2.getGenre()) &&
                Objects.equals(book1.getAvailableCopies(), book2.getAvailableCopies());
    }

    // Validates that 2 users are equivalent without comparing their IDs and passwords
    public static boolean compareUsers(User user1, User user2) {
        return Objects.equals(user1.getName(), user2.getName()) &&
                Objects.equals(user1.getEmail(), user2.getEmail()) &&
                Objects.equals(user1.getRole(), user2.getRole()) &&
                Objects.equals(user1.getPassword(), user2.getPassword());
    }

    // Validates that 2 lists of books are equivalent without comparing their IDs
    public static boolean compareBookLists(List<Book> list1, List<Book> list2) {
        if (list1.size() != list2.size()) return false;

        List<Book> unmatchedBooks = new ArrayList<>(list2);
        return list1.stream().allMatch(book1 -> unmatchedBooks.removeIf(book2 -> compareBooks(book1, book2)));
    }

    public static boolean compareUserLists(List<User> list1, List<User> list2) {
        if (list1.size() != list2.size()) return false;

        List<User> unmatchedUsers = new ArrayList<>(list2);
        return list1.stream().allMatch(user1 -> unmatchedUsers.removeIf(user2 -> compareUsers(user1, user2)));
    }

}
