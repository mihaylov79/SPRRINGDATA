package springdata.bookshop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.bookshop.data.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {



}
