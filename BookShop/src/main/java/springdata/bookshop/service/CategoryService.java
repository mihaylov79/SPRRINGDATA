package springdata.bookshop.service;

import java.io.IOException;

public interface CategoryService {

    void CategorySeed() throws IOException;
    boolean isImported();
}
