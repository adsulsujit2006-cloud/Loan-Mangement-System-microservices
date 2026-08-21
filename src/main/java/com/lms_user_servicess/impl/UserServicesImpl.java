package com.lms_user_servicess.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms_user_services.service.UserServices;
import com.lms_user_servicess.dto.request.UpdateUserRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.UserResponse;
import com.lms_user_servicess.exception.BadRequestException;
import com.lms_user_servicess.exception.DuplicateResourceException;
import com.lms_user_servicess.exception.ResourceNotFoundException;
import com.lms_user_servicess.mapper.UserMapper;
import com.lms_user_servicess.modal.Branch;
import com.lms_user_servicess.modal.User;
import com.lms_user_servicess.repository.BranchRepository;
import com.lms_user_servicess.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServicesImpl implements UserServices {
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
	 * instance of UserMapper
	 */
	@Autowired
	private UserMapper userMapper;
	/*
	 * This implemented method is Registor user
	 */
	@Override
	public UserResponse createUser(UserRegistrationRequest request) {
		/*
		 * check request null or not
		 */
	    if (request == null) {
	        throw new BadRequestException("Please enter appropriate information.");
	    }
	    /*
	     * Add log
	     */
	    log.info("Creating new user account with firstName {}", request.getFirstName());
	    log.info("Creating new user account with middleName {}", request.getMiddleName());
	    log.info("Creating new user account with lastName {}", request.getLastName());

	    log.info("Creating new user account with dateOfBirth {}", request.getDateOfBirth());
	    log.info("Creating new user account with gender {}", request.getGender());
	    /*
	     * check user email exit or not in DB
	     */
	    if (userRepository.existsByEmail(request.getEmail())) {
	        throw new DuplicateResourceException("Email already exists");
	    }
	    /*
	     * check user mobaile number exit or not
	     */
	    if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
	        throw new DuplicateResourceException("Mobile number already exists");
	    }
	    /*
	     * check adhar number is exit or not
	     */
	    if (userRepository.existsByAadhaarNumber(request.getAadhaarNumber())) {
	        throw new DuplicateResourceException("Aadhaar number already exists");
	    }
	    /*
	     * check panNumber exit or not
	     */
	    if (userRepository.existsByPanNumber(request.getPanNumber())) {
	        throw new DuplicateResourceException("PAN number already exists");
	    }
	    /*
	     * To assign branch on user 
	     */
	    Branch branch = branchRepository.findById(request.getBranchId())
	            .orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
	    /*
	     * map data userMapper class 
	     */
	    User user = userMapper.toEntity(request);
	    /*
	     * set by default active
	     */
	    user.setActive(true);
	    /*
	     * branch set for user
	     */
	    user.setBranch(branch);
	    /*
	     * user data save in DB
	     */
	    User savedUser = userRepository.save(user);
	    return userMapper.toResponse(savedUser);
	}
	@Override
	public UserResponse updateUser(Long id, UpdateUserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * This implemented method is Registor user
	 */
	@Override
	@Transactional(readOnly = true)
	public UserResponse getUserById(Long id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found for given id: " + id));
	    return userMapper.toResponse(user);
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse getUserByEmail(String email) {
		 User user = userRepository.findByEmail(email)
		            .orElseThrow(() -> new ResourceNotFoundException("User not found for given id: " + email));
		    return userMapper.toResponse(user);
	}

	@Override
	public UserResponse getUserByMobail(String mobaile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponse> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse activateUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse deActivateUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
