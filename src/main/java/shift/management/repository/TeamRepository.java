package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shift.management.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
