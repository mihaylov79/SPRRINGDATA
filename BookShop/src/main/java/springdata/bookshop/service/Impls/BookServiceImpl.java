package springdata.bookshop.service.Impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import springdata.bookshop.data.entities.Author;
import springdata.bookshop.data.entities.Book;
import springdata.bookshop.data.entities.Category;
import springdata.bookshop.data.entities.Enums.Edition;
import springdata.bookshop.data.entities.Enums.Restriction;
import springdata.bookshop.data.repositories.AuthorRepository;
import springdata.bookshop.data.repositories.BookRepository;
import springdata.bookshop.data.repositories.CategoryRepository;
import springdata.bookshop.service.BookService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private static final String BOOKS_PATH = "src/main/resources/files/books.txt";
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void BookSeed() throws IOException {
        Set<Book>bookz = new HashSet<>();

        Files.readAllLines(Path.of(BOOKS_PATH)).stream().filter(line-> !line.trim().isEmpty())
                .forEach(line-> {
            String[] splitLine = line.split(" ");
            Book book = new Book(mapToEdition(splitLine[0]),
                    LocalDate.parse(splitLine[1], DateTimeFormatter.ofPattern("d/M/yyyy")),
                    Integer.parseInt(splitLine[2]),
                    BigDecimal.valueOf(Double.parseDouble(splitLine[3])),
                    mapToRestriction(splitLine[4]),
                    Arrays.stream(splitLine).skip(5).collect(Collectors.joining()));
                    book.setAuthor(randomAuthor());
                    book.setCategories(randomCategory());
                    bookz.add(book);

        });



        bookRepository.saveAllAndFlush(bookz);
        System.out.printf("%d Books imported",bookRepository.count());

    }

    @Override
    public boolean isImported() {
        return bookRepository.count() > 0;
    }

    private Edition mapToEdition(String code) {
        return switch (code) {
            case "0" -> Edition.NORMAL;
            case "1" -> Edition.PROMO;
            case "2" -> Edition.GOLD;
            default -> throw new IllegalArgumentException("Invalid Edition code: " + code);
        };
    }

    private Restriction mapToRestriction(String code) {
        return switch (code) {
            case "0" -> Restriction.MINOR;
            case "1" -> Restriction.TEEN;
            case "2" -> Restriction.ADULT;
            default -> throw new IllegalArgumentException("Invalid Restriction code: " + code);
        };
    }

    private Author randomAuthor() {
     Integer authorId = ThreadLocalRandom.current().nextInt(1, (int) (authorRepository.count()+1));
        Optional<Author> author = this.authorRepository.findById(authorId);
        return author.orElse(null);
    }

    private Set<Category> randomCategory() {
        Set<Category>categoriesSet = new HashSet<>();
        int count = ThreadLocalRandom.current().nextInt(1,8);

        for (int i = 0; i < count; i++) {
            Integer categoryId = ThreadLocalRandom.current().nextInt(1, (int) (categoryRepository.count() +1));
            categoriesSet.add(this.categoryRepository.findById(categoryId).get());
        }

        return categoriesSet;
    }
}
