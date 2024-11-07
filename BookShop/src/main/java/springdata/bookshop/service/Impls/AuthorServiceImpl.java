package springdata.bookshop.service.Impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdata.bookshop.data.entities.Author;
import springdata.bookshop.data.repositories.AuthorRepository;
import springdata.bookshop.service.AuthorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_PATH = "src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void AuthorSeed() throws IOException {

        Files.readAllLines(Path.of(AUTHOR_PATH)).stream().filter(line-> !line.trim().isEmpty())
                .forEach(line-> {
                   String[] splitLine = line.split(" ");
                    Author author = new Author(splitLine[0],splitLine[1]);
                    authorRepository.saveAndFlush(author);


                });
         System.out.printf("%d Authors imported", authorRepository.count());

    }

    @Override
    public boolean isImported() {
        return authorRepository.count() > 0;
    }
}
