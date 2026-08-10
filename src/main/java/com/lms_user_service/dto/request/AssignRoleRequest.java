package com.lms_user_service.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignRoleRequest {
	@NotNull(message = "User ID is required")
	private long userId;

	@NotNull(message = "Role ID is required")
	private Long roleId;

}
