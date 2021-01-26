package ro.delivery.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.delivery.products.entity.Borrow;
import ro.delivery.products.entity.Reader;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
