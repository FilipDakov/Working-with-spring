package com.example.spring_demo.repository;

import com.example.spring_demo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("SELECT a from Author as a order by a.books.size desc")
    List<Author> getAuthorsOrderByBooksDesc();

    List<Author> getAllByFirstNameEndsWith(String ends);
    Author getFirstByLastNameStartingWith(String prefix);

}
