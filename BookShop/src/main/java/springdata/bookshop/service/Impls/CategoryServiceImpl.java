package springdata.bookshop.service.Impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdata.bookshop.data.entities.Category;
import springdata.bookshop.data.repositories.CategoryRepository;
import springdata.bookshop.service.CategoryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    @Override
    public void CategorySeed() throws IOException {
        Set<Category>categories = new HashSet<>();

        Files.readAllLines(Path.of(CATEGORY_PATH)).stream().filter(line-> !line.isBlank()).forEach(line-> {
                    Category category = new Category(line);
                    categories.add(category);

                    categoryRepository.saveAllAndFlush(categories);
                });


        System.out.printf("%d Categories imported", this.categoryRepository.count());
    }

    @Override
    public boolean isImported() {

        return this.categoryRepository.count() > 0;
    }
}
