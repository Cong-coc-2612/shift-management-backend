package shift.management.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import shift.management.entity.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

	Page<Shift> findByUserId(Long id, Pageable pageable);
	
	Page<Shift> findByTeamId(Long id, Pageable pageable);
	
	Page<Shift> findByTeamIdAndFullNameLike(Long id, String fullName, Pageable pageable);

	List<Shift> findByUserId(Long id, Sort sort);
}
