package com.lms_user_service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.lms_user_service.dto.request.CreatePermissionRequest;
import com.lms_user_service.dto.request.UpdatePermissionRequest;
import com.lms_user_service.dto.responce.PermissionResponse;
import com.lms_user_service.modal.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
	Permission toEntity(CreatePermissionRequest request);

    Permission toEntity(UpdatePermissionRequest request);

    PermissionResponse toResponse(Permission permission);

    List<PermissionResponse> toResponseList(List<Permission> permissions);

}
