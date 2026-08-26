package com.lms_user_servicess.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms_user_services.service.Branch_ManagerService;
import com.lms_user_services.service.UserServices;
import com.lms_user_servicess.dto.request.UpdateUserRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.UserResponse;
import com.lms_user_servicess.enums.RoleType;
import com.lms_user_servicess.mapper.UserMapper;
import com.lms_user_servicess.modal.Role;
import com.lms_user_servicess.modal.User;
import com.lms_user_servicess.repository.BranchRepository;
import com.lms_user_servicess.repository.RoleRepository;
import com.lms_user_servicess.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Branch_ManagerServiceImpl implements Branch_ManagerService {

	/*
	 * instance of UserRepo
	 */
	@Autowired
	private UserRepository userRepository;

	/*
	 * instance of BranchRepo
	 */
	@Autowired
	private BranchRepository branchRepository;

	/*
	 * instance of RoleRepo
	 */
	@Autowired
	private RoleRepository roleRepository;

	/*
	 * instance of UserMapper
	 */
	@Autowired
	private UserMapper userMapper;

	/*
	 * instance of userServices
	 */
	@Autowired
	private UserServices userServices;

	/*
	 * This implemented method is Register Branch Managar
	 */
	@Override
	public UserResponse registorBM(UserRegistrationRequest request) {

		/*
		 * Check request
		 */
		if (request == null) {
			throw new IllegalArgumentException("User registration request cannot be null");
		}

		/*
		 * Add log
		 */
		log.info("Registering Branch Manager user: {}", request);

		/*
		 * Create user
		 */
		UserResponse response = userServices.createUser(request);

		/*
		 * Return created user response
		 */
		return response;
	}

	/*
	 * This implemented method is assign role
	 */
	@Override
	@Transactional
	public UserResponse assignRoleRE(Long id, UpdateUserRequest request) {
		/*
		 * Add log
		 */
		log.info("assign role by using id {}", id);
		/*
		 * Check user id
		 */
		if (id == null) {
			throw new IllegalArgumentException("User id cannot be null");
		}

		/*
		 * Check request
		 */
		if (request == null) {
			throw new IllegalArgumentException("Update user request cannot be null");
		}

		/*
		 * Find user
		 */
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));

		/*
		 * Find Representative Executive role
		 */
		Role role = roleRepository.findByRoleName(RoleType.REPRESENTATIVE_EXECUTIVE)
				.orElseThrow(() -> new RuntimeException("Representative Executive role not found"));

		/*
		 * Check whether role is already assigned
		 */
		if (user.getRoles().contains(role)) {
			throw new RuntimeException("Representative Executive role is already assigned to user id: " + id);
		}

		/*
		 * Assign role
		 */
		user.getRoles().add(role);

		/*
		 * Update user
		 */
		User updatedUser = userRepository.save(user);

		/*
		 * Log
		 */
		log.info("Representative Executive role assigned successfully to user id: {}", id);

		/*
		 * Return response
		 */
		return userMapper.toResponse(updatedUser);
	}

}