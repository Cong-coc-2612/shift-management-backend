package shift.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shift.management.entity.Team;
import shift.management.service.TeamService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/teams")
public class TeamController {
	@Autowired
	private TeamService teamService;

	@GetMapping("")
	public ResponseEntity<List<Team>> getAllTeam() {
		var response = teamService.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
