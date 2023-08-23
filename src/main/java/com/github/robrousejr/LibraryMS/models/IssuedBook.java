package com.github.robrousejr.LibraryMS.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.Date;

@Entity
public class IssuedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonView
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView
    private User user;

    @Setter
    @JsonView
    private Date issueDate;

    @Setter
    @JsonView
    private Date returnDate;
}
