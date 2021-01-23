package ro.delivery.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.delivery.products.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
}
