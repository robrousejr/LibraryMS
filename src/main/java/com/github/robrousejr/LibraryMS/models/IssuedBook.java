package com.github.robrousejr.LibraryMS.models;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.Date;

@Entity
public class IssuedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    private Date issueDate;

    @Setter
    private Date returnDate;
}
