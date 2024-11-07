package springdata.bookshop.service;

import java.io.IOException;

public interface AuthorService {

    void AuthorSeed() throws IOException;

    boolean isImported();
}
