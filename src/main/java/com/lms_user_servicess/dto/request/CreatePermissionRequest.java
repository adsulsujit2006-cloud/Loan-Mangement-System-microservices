package com.lms_user_servicess.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lms_user_servicess.enums.ModuleType;
import com.lms_user_servicess.enums.PermissionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermissionRequest {

    @NotNull(message = "Permission Name is required")
    private PermissionType permissionName;

    @NotNull(message = "Module Name is required")
    private ModuleType moduleName;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

}
