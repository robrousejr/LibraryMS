package com.github.robrousejr.LibraryMS.repositories;
import com.github.robrousejr.LibraryMS.models.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {

}
