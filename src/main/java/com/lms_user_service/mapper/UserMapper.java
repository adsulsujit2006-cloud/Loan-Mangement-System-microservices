package com.lms_user_service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lms_user_service.dto.request.UpdateUserRequest;
import com.lms_user_service.dto.request.UserRegistrationRequest;
import com.lms_user_service.dto.responce.UserResponse;
import com.lms_user_service.dto.responce.UserSummaryResponse;
import com.lms_user_service.modal.User;

@Mapper(componentModel = "spring", uses = { RoleMapper.class, BranchMapper.class, AddressMapper.class })
public interface UserMapper {
	User toEntity(UserRegistrationRequest request);

	User toEntity(UpdateUserRequest request);

	@Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
	UserResponse toResponse(User user);

	@Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
	UserSummaryResponse toSummary(User user);

	List<UserResponse> toResponseList(List<User> users);

}
