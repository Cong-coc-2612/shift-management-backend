package shift.management.service;

import java.util.List;

import shift.management.dto.GetListUserRes;

public interface UserService {
	List<GetListUserRes> getAllbyTeamId(Long teamId, String fullName);
	
	GetListUserRes get(Long id);
}
