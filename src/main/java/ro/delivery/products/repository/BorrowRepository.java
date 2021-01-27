package ro.delivery.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.delivery.products.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
