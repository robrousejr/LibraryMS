package com.github.robrousejr.LibraryMS.repositories;
import com.github.robrousejr.LibraryMS.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
