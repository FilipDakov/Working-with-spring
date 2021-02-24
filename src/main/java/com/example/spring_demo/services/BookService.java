package com.example.spring_demo.services;

import com.example.spring_demo.entities.Author;
import com.example.spring_demo.entities.Book;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    public List<Author> getAuthorBefore2000();
    public List<Book> getBooksAfter2000();
    public List<Book> getBooksAfterDate(String date);
    public List<Book> getBooksTitleContains(String input);
    public int getBooksWithTitleLongerThan(int length);
    public int removeBooksByCopies(int copies);
    public int numberOfBooksByAuthor(String firstName,String lastName);
}
