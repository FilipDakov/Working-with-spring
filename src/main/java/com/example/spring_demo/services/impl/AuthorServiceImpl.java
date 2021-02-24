package com.example.spring_demo.services.impl;

import com.example.spring_demo.constants.GlobalConstants;
import com.example.spring_demo.entities.Author;
import com.example.spring_demo.entities.Book;
import com.example.spring_demo.repository.AuthorRepository;
import com.example.spring_demo.services.AuthorService;
import com.example.spring_demo.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthor() throws IOException {
        if(authorRepository.count()!=0){
            return;
        }

        String[] fileContent = fileUtil.readFileContent(GlobalConstants.AUTHORS_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(a ->{
                    String[] tokens = a.split("\\s+");
                    Author author = new Author(tokens[0],tokens[1]);
                    authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public List<Author> getAuthor() {
      return   this.authorRepository.getAuthorsOrderByBooksDesc();
    }

    @Override
    public List<Author> getAuthorsEndingWith(String suffix) {
        return this.authorRepository.getAllByFirstNameEndsWith(suffix);
    }


    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public Set<Book> getAuthorLastNameStartingWith(String prefix) {
        return this.authorRepository.getFirstByLastNameStartingWith(prefix).getBooks();
    }
}
