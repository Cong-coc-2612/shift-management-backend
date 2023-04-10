package shift.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shift.management.entity.Team;
import shift.management.repository.TeamRepository;
import shift.management.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	private TeamRepository teamRepository;
	
	@Override
	public List<Team> getAll() {
		return teamRepository.findAll();
	}

}
