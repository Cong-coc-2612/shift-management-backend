package shift.management.controllers;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shift.management.dto.CreateShiftReq;
import shift.management.dto.EditShiftReq;
import shift.management.dto.GetShiftRes;
import shift.management.service.ShiftService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

	@Autowired
	ShiftService shiftService;

	@GetMapping("")
	public ResponseEntity<Map<String, Object>> getAllShiftPage(@RequestParam(required = false) String fullName,
			@RequestParam(required = false) Long teamId, @RequestParam(required = false) Long userId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id,desc") String[] sort) {
		System.out.println("fullName: " + fullName);
		System.out.println("teamId: " + teamId);
		System.out.println("userId: " + userId);
		var response = shiftService.getAllShiftPage(fullName, teamId, userId, page, size, sort);
		if (Objects.nonNull(response)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetShiftRes> getShiftById(@PathVariable("id") long id) {
		return new ResponseEntity<>(shiftService.getShiftById(id), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Void> createShift(@RequestBody CreateShiftReq shift) {
		shiftService.createShift(shift);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateShift(@PathVariable("id") long id, @RequestBody EditShiftReq shift) {
		shiftService.updateShift(id, shift);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Void> updateShiftStatus(@PathVariable("id") long id, @RequestParam String status) {
		shiftService.updateShiftStatus(id, status);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteShift(@PathVariable("id") long id) {
		shiftService.deleteShift(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
