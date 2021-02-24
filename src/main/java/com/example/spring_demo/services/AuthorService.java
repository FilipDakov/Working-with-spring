package com.example.spring_demo.services;


import com.example.spring_demo.entities.Author;
import com.example.spring_demo.entities.Book;
import net.bytebuddy.asm.Advice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AuthorService {
    void seedAuthor() throws IOException;

    int getAuthorsCount();

    List<Author> getAuthor();

    List<Author> getAuthorsEndingWith(String suffix);

    Author findAuthorById(Long id);

    Set<Book> getAuthorLastNameStartingWith(String prefix);
}
