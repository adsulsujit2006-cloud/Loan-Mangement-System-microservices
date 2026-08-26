package com.lms_user_services.service;

import com.lms_user_servicess.dto.request.CreateBranchRequest;
import com.lms_user_servicess.dto.request.LoginRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.LoginResponse;
import com.lms_user_servicess.dto.responce.UserResponse;

public interface REPRESENTATIVE_EXECUTIVE {
	
	public UserResponse registorRE(UserRegistrationRequest request);
	
	public UserResponse registorUser(CreateBranchRequest request);
	
	public LoginResponse RElogin(LoginRequest request);
	
	public LoginResponse userlogin(LoginRequest request);

}
