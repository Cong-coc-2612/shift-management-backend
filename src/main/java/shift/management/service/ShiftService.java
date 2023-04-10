package shift.management.service;

import java.util.Map;

import shift.management.dto.CreateShiftReq;
import shift.management.dto.EditShiftReq;
import shift.management.dto.GetShiftRes;

public interface ShiftService {
	Map<String, Object> getAllShiftPage(String fullName, Long teamId, Long userId, int page, int size, String[] sort);

	GetShiftRes getShiftById(long id);

	void createShift(CreateShiftReq shift);
	
	void deleteShift(long id);

	void updateShift(Long id, EditShiftReq shiftDto);
	
	void updateShiftStatus(Long id, String status);
}
