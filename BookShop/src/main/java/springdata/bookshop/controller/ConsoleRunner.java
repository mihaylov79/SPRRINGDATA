package springdata.bookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdata.bookshop.data.repositories.AuthorRepository;
import springdata.bookshop.service.AuthorService;
import springdata.bookshop.service.BookService;
import springdata.bookshop.service.CategoryService;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public ConsoleRunner(CategoryService categoryService, BookService bookService, AuthorService authorService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.authorService = authorService;

    }

    @Override
    public void run(String... args) throws Exception {
        if(!this.categoryService.isImported()){
            this.categoryService.CategorySeed();
        }
        if (!this.authorService.isImported()){
            this.authorService.AuthorSeed();
        }

        if(!this.bookService.isImported()){
            this.bookService.BookSeed();
        }

    }
}
