package com.lms_user_servicess.dto.responce;

import java.time.LocalDateTime;

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
public class PermissionResponse {

    private Long id;

    private PermissionType permissionName;

    private ModuleType moduleName;

    private String description;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}	