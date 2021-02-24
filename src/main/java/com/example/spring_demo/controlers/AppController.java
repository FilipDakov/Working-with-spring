package com.example.spring_demo.controlers;

import com.example.spring_demo.entities.Author;
import com.example.spring_demo.entities.Book;
import com.example.spring_demo.services.AuthorService;
import com.example.spring_demo.services.BookService;
import com.example.spring_demo.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private final AuthorService authorServiceService;
    private final CategoryService categoryService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public AppController(AuthorService authorService, CategoryService categoryService, BookService bookService, BufferedReader bufferedReader) {
        this.authorServiceService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorServiceService.seedAuthor();
        this.bookService.seedBooks();


    /*    //5 Books Released Before Date
        System.out.println("Enter year:");
        this.bookService.getBooksAfterDate(bufferedReader.readLine())
                .forEach(b -> System.out.printf("%s %s %.2f %n",b.title,b.editionType,b.price));
*/

      /*  //6 Authors Search
        System.out.println("Enter suffix:");
        this.authorServiceService.getAuthorsEndingWith(bufferedReader.readLine().trim())
                .forEach(a-> System.out.printf("%s %s %n",a.getFirstName(),a.getLastName()));*/

     /*   //7 Books Search
        System.out.println("Enter part of a title:");
        this.bookService.getBooksTitleContains(bufferedReader.readLine().trim())
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);*/

    /*    //8 Book Titles Search
        System.out.println("Enter prefix");
        this.authorServiceService.getAuthorLastNameStartingWith(bufferedReader.readLine())
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);*/
 /*       //9 Count Books
        System.out.println("Enter minimum title length: ");
        int x = Integer.parseInt(bufferedReader.readLine());
        System.out.printf("There are %d books with longer title than %d symbols",
                this.bookService.getBooksWithTitleLongerThan(x),x);*/

/*        // 10 delete a data
        System.out.println("Enter copies : ");
        int i = this.bookService.removeBooksByCopies(Integer.parseInt(bufferedReader.readLine()));
        System.out.println(i);*/

        // 11  stored procedure
        System.out.println("Enter name of the Author");
        String[] params = this.bufferedReader.readLine().split("\\s+");
        System.out.println(this.bookService.numberOfBooksByAuthor(params[0],params[1]));

    }
}
