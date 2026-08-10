package com.lms_user_service.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lms_user_service.domain.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequest {

    @NotNull(message = "Role is required")
    private RoleType roleName;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;
    

}