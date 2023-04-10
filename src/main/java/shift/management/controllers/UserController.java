package shift.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shift.management.dto.GetListUserRes;
import shift.management.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("")
	public ResponseEntity<List<GetListUserRes>> getUserByTeamId(@RequestParam(required = false) Long teamId,
			@RequestParam(required = false) String fullName) {
		var response = userService.getAllbyTeamId(teamId, fullName);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetListUserRes> get(@PathVariable("id") long id) {
		return new ResponseEntity<GetListUserRes>(userService.get(id), HttpStatus.OK);
	}
}
