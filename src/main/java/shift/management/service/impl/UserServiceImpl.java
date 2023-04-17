package shift.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shift.management.dto.GetListUserRes;
import shift.management.entity.User;
import shift.management.repository.TeamRepository;
import shift.management.repository.UserRepository;
import shift.management.repository.WorkplaceRepository;
import shift.management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private WorkplaceRepository workplaceRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<GetListUserRes> getAllbyTeamId(Long teamId, String fullName) {
		List<GetListUserRes> listRes = new ArrayList<>();
		List<User> listUser = new ArrayList<>();
		if (fullName != null) {
			listUser = userRepository.findAllByTeamIdAndFullnameLike(Objects.nonNull(teamId) ? teamId : 1,
					"%" + fullName + "%");
		} else {
			listUser = userRepository.findAllByTeamId(Objects.nonNull(teamId) ? teamId : 1);
		}
		listRes = listUser.stream().map(user -> {
			GetListUserRes userRes = mapper.map(user, GetListUserRes.class);
			userRes.setGender(user.getGender() ? "Male" : "Female");
			userRes.setTeamName(
					Objects.nonNull(user.getTeamId()) ? teamRepository.findById(user.getTeamId()).get().getTeamName()
							: "");
			userRes.setWorkplaceName(Objects.nonNull(user.getWorkplaceId())
					? workplaceRepository.findById(user.getWorkplaceId()).get().getWorkplaceName()
					: "");
			return userRes;
		}).collect(Collectors.toList());

		return listRes;
	}

	@Override
	public GetListUserRes get(Long id) {
		var user = userRepository.findById(id).get();
		GetListUserRes userRes = mapper.map(user, GetListUserRes.class);
		userRes.setGender(user.getGender() ? "Male" : "Female");
		userRes.setTeamName(
				Objects.nonNull(user.getTeamId()) ? teamRepository.findById(user.getTeamId()).get().getTeamName() : "");
		userRes.setWorkplaceName(Objects.nonNull(user.getWorkplaceId())
				? workplaceRepository.findById(user.getWorkplaceId()).get().getWorkplaceName()
				: "");
		return userRes;
	}
}
