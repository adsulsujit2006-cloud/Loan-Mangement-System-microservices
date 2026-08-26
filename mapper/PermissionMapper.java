package com.lms_user_servicess.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.lms_user_servicess.dto.request.CreatePermissionRequest;
import com.lms_user_servicess.dto.request.UpdatePermissionRequest;
import com.lms_user_servicess.dto.responce.PermissionResponse;
import com.lms_user_servicess.modal.Permission;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
	Permission toEntity(CreatePermissionRequest request);

    Permission toEntity(UpdatePermissionRequest request);

    PermissionResponse toResponse(Permission permission);

    List<PermissionResponse> toResponseList(List<Permission> permissions);

}