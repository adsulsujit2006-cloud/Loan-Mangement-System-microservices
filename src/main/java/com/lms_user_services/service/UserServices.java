package com.lms_user_services.service;

import java.util.List;

import javax.validation.Valid;

import com.lms_user_servicess.dto.request.UpdateBranchRequest;
import com.lms_user_servicess.dto.request.UpdateUserRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.UserResponse;

public interface UserServices {
	public UserResponse createUser(UserRegistrationRequest request);
	public UserResponse updateUser(Long id,@Valid UpdateUserRequest request);
	public UserResponse getUserById(Long id);
	public UserResponse getUserByEmail(String email);
	public UserResponse getUserByMobail(String mobaile);
	public UserResponse getUserByAdharNo(String aadhaarNumber);
	public List<UserResponse> getAllUsers();
	public ApiResponse deleteUser(Long id);
	public ApiResponse activateUser(Long id);
	public ApiResponse deActivateUser(Long id);
	

}


