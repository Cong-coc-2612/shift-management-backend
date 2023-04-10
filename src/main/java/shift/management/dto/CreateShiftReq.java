package shift.management.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShiftReq {
	private int shiftNumber;
	private String status;
	private List<Long> userId;
	private Long teamId;
	private String dayStart;
	private String description;
}
