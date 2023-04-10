package shift.management.dto;

import java.time.LocalDate;

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
public class GetListUserRes {
	private Long id;
	private String employeeCode;
	private String username;
	private String fullname;
	private String email;
	private String phone;
	private LocalDate dob;
	private String gender;
	private String workplaceName;
	private String teamName;

}
