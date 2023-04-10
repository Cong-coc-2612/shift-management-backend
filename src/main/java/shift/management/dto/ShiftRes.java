package shift.management.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShiftRes {
	private Long id;
	private int shiftNumber;
	private LocalDate dayStart;
	private String status;
	private Long teamId;
	private Long userId;
	private String description;
	private LocalDateTime createAt;
	private String employeeCode;
	private String employeeName;
}
