package springdata.bookshop.service;

import java.io.IOException;

public interface BookService {

    void BookSeed() throws IOException;
    boolean isImported();

//    boolean areBooksImported();
}
