package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shift.management.entity.Workplace;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
}
