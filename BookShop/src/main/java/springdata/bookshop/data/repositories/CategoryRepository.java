package springdata.bookshop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.bookshop.data.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
