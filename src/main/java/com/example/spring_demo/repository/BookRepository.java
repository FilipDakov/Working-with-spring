package com.example.spring_demo.repository;

import com.example.spring_demo.entities.Author;
import com.example.spring_demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    @Query("SELECT a.author from Book as a WHERE a.releaseDate < :date")
    List<Author> getAuthorBefore2000(@Param("date") LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByTitleContaining(String input);

    @Modifying
    @Query("DELETE FROM Book b WHERE b.copies < :copies")
    int deleteBooksByCopiesLessThan(@Param("copies") int copies);

    @Procedure("GetBooksByAuthorTwo")
    int getBooksByAuthor(String firstName,String lastName);

}
