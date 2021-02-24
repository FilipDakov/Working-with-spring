package com.example.spring_demo.services.impl;

import com.example.spring_demo.constants.GlobalConstants;
import com.example.spring_demo.entities.*;
import com.example.spring_demo.repository.BookRepository;
import com.example.spring_demo.services.AuthorService;
import com.example.spring_demo.services.BookService;
import com.example.spring_demo.services.CategoryService;
import com.example.spring_demo.utils.FileUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;

        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {

        if (this.bookRepository.count() != 0){
            return;
        }


        String[] fileContent = fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(b -> {
                    String[] tokens = b.split("\\s+");
                    Author author= getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(tokens[1],formatter);

                    int copies = Integer.parseInt(tokens[2]);
                    BigDecimal price = new BigDecimal(tokens[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];
                    
                    String title = getTitle(tokens);

                    Set<Category> categories = getRandomCategories();
                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);
                    book.setCategories(categories);
                    this.bookRepository.saveAndFlush(book);
                });
    }

    @Override
    public List<Author> getAuthorBefore2000() {
        LocalDate localDate = LocalDate.of(1988,01,01);
        return this.bookRepository.getAuthorBefore2000(localDate);
    }

    @Override
    public List<Book> getBooksAfter2000() {
        LocalDate localDate = LocalDate.of(2000,12,31);
        return bookRepository.findAllByReleaseDateAfter(localDate);
    }

    @Override
    public List<Book> getBooksAfterDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate releaseDate = LocalDate.parse(date,formatter);
        return bookRepository.findAllByReleaseDateBefore(releaseDate);

    }

    @Override
    public List<Book> getBooksTitleContains(String input) {
        return this.bookRepository.findAllByTitleContaining(input.toLowerCase());
    }

    @Override
    public int getBooksWithTitleLongerThan(int length) {

        List<Book> collect = this.bookRepository.
                findAll().stream()
                .filter(book -> book.getTitle().length() > length).collect(Collectors.toList());

        return collect.size();
    }

    @Override
    public int removeBooksByCopies(int copies) {
        return this.bookRepository.deleteBooksByCopiesLessThan(copies);
    }

    @Override
    public int numberOfBooksByAuthor(String firstName, String lastName) {
        return this.bookRepository.getBooksByAuthor(firstName,lastName);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> result= new HashSet<>();
        Random random = new Random();

        int bound = random.nextInt(3)+1;



        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.categoryService.getCategoryById((long) categoryId));
        }

        return result;
    }

    private String getTitle(String[] tokens) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 5; i < tokens.length; i++) {
            stringBuilder.append(tokens[i]).append(" ");
        }
        return  stringBuilder.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(authorService.getAuthorsCount())+ 1;

        return  this.authorService.findAuthorById((long) randomId);
    }
}
