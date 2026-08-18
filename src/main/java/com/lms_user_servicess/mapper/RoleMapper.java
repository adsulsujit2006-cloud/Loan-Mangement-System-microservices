package com.lms_user_servicess.mapper;

import org.mapstruct.Mapper;

import com.lms_user_servicess.dto.request.CreateRoleRequest;
import com.lms_user_servicess.dto.responce.RoleResponse;
import com.lms_user_servicess.modal.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	RoleResponse toResponse(Role role);
	Role toEntity(CreateRoleRequest request);

}
