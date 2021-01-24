package ro.delivery.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.delivery.products.entity.Author;
import ro.delivery.products.entity.Category;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
