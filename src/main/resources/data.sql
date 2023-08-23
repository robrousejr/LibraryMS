INSERT INTO BOOK (title, author, genre, isbn, available_copies) VALUES ('Hunger Games', 'Suzanne Collins', 'Fantasy', '978-3-2112', 29);
INSERT INTO BOOK (title, author, genre, isbn, available_copies) VALUES ('1984', 'George Orwell', 'Dystopian', '978-0-4522', 15);
INSERT INTO BOOK (title, author, genre, isbn, available_copies) VALUES ('To Kill a Mockingbird', 'Harper Lee', 'Classic', '978-0-0620', 12);
INSERT INTO BOOK (title, author, genre, isbn, available_copies) VALUES ('The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', '978-0-6185', 20);
INSERT INTO BOOK (title, author, genre, isbn, available_copies) VALUES ('Pride and Prejudice', 'Jane Austen', 'Romance', '978-1-5930', 18);
INSERT INTO BOOK (title, author, genre, isbn, available_copies) VALUES ('Harry Potter and the Sorcerers Stone', 'J.K. Rowling', 'Fantasy', '978-0-4395', 25);

INSERT INTO ISSUED_BOOK(id, book_id, user_id, issue_date, return_date) VALUES (1, 1, 2, DATE '2020-01-01', DATE '2020-01-15');
INSERT INTO ISSUED_BOOK(id, book_id, user_id, issue_date, return_date) VALUES (2, 2, 2, DATE '2021-08-11', DATE '2023-05-20');
INSERT INTO ISSUED_BOOK(id, book_id, user_id, issue_date, return_date) VALUES (3, 3, 2, DATE '2022-08-17', NULL);