package springdata.bookshop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.bookshop.data.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
