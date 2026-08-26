package com.lms_user_services.service;

import com.lms_user_servicess.dto.request.UpdateUserRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.UserResponse;

public interface Branch_ManagerService {
	public UserResponse registorBM(UserRegistrationRequest request);
	
	public UserResponse assignRoleRE(Long id,UpdateUserRequest request);

}


