package com.lms_user_servicess.sservice;

import java.util.List;

import com.lms_user_servicess.dto.request.CreatePermissionRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.PermissionResponse;

public interface PermissionServices {

    public PermissionResponse addPermission(CreatePermissionRequest request);

    public PermissionResponse getPermissionById(Long id);

    public List<PermissionResponse> getAllPermissions();

    public ApiResponse activatePermission(Long id);

    public ApiResponse deActivatePermission(Long id);

    public ApiResponse deletePermission(Long id);
}