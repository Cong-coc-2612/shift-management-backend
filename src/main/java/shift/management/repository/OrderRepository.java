package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shift.management.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
