package com.lms_user_services.service;

import java.util.List;

import com.lms_user_servicess.dto.request.CreateRoleRequest;
import com.lms_user_servicess.dto.request.UpdateRoleRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.RoleResponse;

public interface RoleServices {
	public RoleResponse createRole(CreateRoleRequest request);

	public RoleResponse updateRole(Long id, UpdateRoleRequest request);

	public RoleResponse getRoleById(Long id);

	public List<RoleResponse> getAllRole();

	public ApiResponse deleteRole(Long id);

	public ApiResponse ActiveRole(Long id);

	public ApiResponse deActivateRole(Long id);

}
