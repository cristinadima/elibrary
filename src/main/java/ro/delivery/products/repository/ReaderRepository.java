package ro.delivery.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.delivery.products.entity.Category;
import ro.delivery.products.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
}
