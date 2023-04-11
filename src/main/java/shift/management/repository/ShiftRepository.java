package shift.management.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import shift.management.entity.Shift;

public interface ShiftRepository extends JpaRepository<Shift, Long> {

	Page<Shift> findByUserId(Long id, Pageable pageable);
	
	Page<Shift> findByTeamId(Long id, Pageable pageable);
	
	Page<Shift> findByTeamIdAndFullNameLike(Long id, String fullName, Pageable pageable);
	
	Page<Shift> findByUserIdAndDayStartBetween(Long id, LocalDate startDate, LocalDate endDate, Pageable pageable);
	
	Page<Shift> findByTeamIdAndDayStartBetween(Long id, LocalDate startDate, LocalDate endDate, Pageable pageable);
	
	Page<Shift> findByTeamIdAndFullNameLikeAndDayStartBetween(Long id, String fullName, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
