package shift.management.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import shift.management.dto.CreateShiftReq;
import shift.management.dto.EditShiftReq;
import shift.management.dto.GetShiftRes;
import shift.management.dto.ShiftRes;
import shift.management.entity.Shift;
import shift.management.repository.ShiftRepository;
import shift.management.repository.UserRepository;
import shift.management.service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService {
	@Autowired
	private ShiftRepository shiftRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	public Map<String, Object> getAllShiftPage(String fullName, Long teamId, Long userId, int page, int size,
			String[] sort) {
		try {
			List<Order> orders = new ArrayList<Order>();

			if (sort[0].contains(",")) {
				// will sort more than 2 fields
				// sortOrder="field, direction"
				for (String sortOrder : sort) {
					String[] _sort = sortOrder.split(",");
					orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
				}
			} else {
				// sort=[field, direction]
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}

			List<ShiftRes> shifts = new ArrayList<ShiftRes>();
			Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

			Page<Shift> pageShifts;
			if (fullName == null) {
				if (teamId == null) {
					if (userId == null) {
						pageShifts = shiftRepository.findAll(pagingSort);
					} else {
						pageShifts = shiftRepository.findByUserId(userId, pagingSort);
					}
				} else {
					pageShifts = shiftRepository.findByTeamId(teamId, pagingSort);
				}
			} else {
				pageShifts = shiftRepository.findByTeamIdAndFullNameLike(teamId, "%" + fullName + "%", pagingSort);
			}

			shifts = pageShifts.getContent().stream().map(shift -> {
				System.out.println("userId: " + shift.getUserId());
				ShiftRes shiftRes = mapper.map(shift, ShiftRes.class);
				var user = userRepository.findById(shift.getUserId()).get();
				shiftRes.setEmployeeCode(user.getEmployeeCode());
				shiftRes.setEmployeeName(user.getFullname());
				return shiftRes;
			}).collect(Collectors.toList());

			Map<String, Object> response = new HashMap<>();
			response.put("shifts", shifts);
			response.put("currentPage", pageShifts.getNumber());
			response.put("totalItems", pageShifts.getTotalElements());
			response.put("totalPages", pageShifts.getTotalPages());

			return response;
		} catch (Exception e) {
			return null;
		}
	}

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	@Override
	public GetShiftRes getShiftById(long id) {
		var shift = shiftRepository.findById(id).get();
		GetShiftRes shiftRes = mapper.map(shift, GetShiftRes.class);
		var user = userRepository.findById(shift.getUserId()).get();
		shiftRes.setEmployeeCode(user.getEmployeeCode());
		shiftRes.setEmployeeName(user.getFullname());
		shiftRes.setDayStart(shift.getDayStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		return shiftRes;
	}

	@Override
	public void createShift(CreateShiftReq shift) {
		shift.getUserId().forEach(userId -> {
			System.out.println(userId);
			var user = userRepository.findById(userId).get();
			Shift shiftSave = Shift.builder().shiftNumber(shift.getShiftNumber())
					.dayStart(LocalDate.parse(shift.getDayStart())).createAt(LocalDateTime.now())
					.description(shift.getDescription()).teamId(shift.getTeamId()).userId(userId)
					.fullName(user.getFullname()).status(shift.getStatus()).build();
			shiftRepository.save(shiftSave);
		});
	}

	@Override
	public void updateShift(Long id, EditShiftReq shiftDto) {
		var shiftData = shiftRepository.findById(id);
		if (shiftData.isPresent()) {
			Shift shift = shiftData.get();
			shift.setDayStart(LocalDate.parse(shiftDto.getDayStart()));
			shift.setDescription(shiftDto.getDescription());
			shift.setShiftNumber(shiftDto.getShiftNumber());
			shift.setStatus(shiftDto.getStatus());
			shiftRepository.save(shift);
		}
	}

	@Override
	public void deleteShift(long id) {
		shiftRepository.deleteById(id);
	}

	@Override
	public void updateShiftStatus(Long id, String status) {
		var shiftData = shiftRepository.findById(id);
		if (shiftData.isPresent()) {
			Shift shift = shiftData.get();
			shift.setStatus(status);
			shiftRepository.save(shift);
		}

	}
}
