package ro.delivery.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.delivery.products.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
