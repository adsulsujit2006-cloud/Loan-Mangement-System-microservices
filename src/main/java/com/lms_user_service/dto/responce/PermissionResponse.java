package com.lms_user_service.dto.responce;

import java.time.LocalDateTime;

import com.lms_user_service.domain.ModuleType;
import com.lms_user_service.domain.PermissionType;

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