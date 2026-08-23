package com.lms_user_servicess.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms_user_services.service.UserServices;
import com.lms_user_servicess.dto.request.UpdateBranchRequest;
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
import com.lms_user_servicess.util.CustomerCodeGenerator;

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
	    user.setCustomerCode(CustomerCodeGenerator.generateCustomerCodeWithDate());
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
	
	/*
	 * This implemented method is get user Details by using id 
	 */
	/*
	 * use Transactional annotation to read data 
	 */
	@Override
	@Transactional(readOnly = true)
	public UserResponse getUserById(Long id) {
		/*
		 * Add log
		 */
		log.info("Get user deatils by using user id{}",id);
		/*
		 * find user data in DB
		 */
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found for given id: " + id));
	    return userMapper.toResponse(user);
	}
	/*
	 * This implemented method is get user Details by using email
	 */
	/*
	 * use Transactional annotation to read data 
	 */
	@Override
	@Transactional(readOnly = true)
	public UserResponse getUserByEmail(String email) {
		/*
		 * Add log
		 */
		log.info("Get user deatils by using user email{}",email);
		/*
		 * check user details in DB
		 */
		 User user = userRepository.findByEmail(email)
		            .orElseThrow(() -> new ResourceNotFoundException("User not found for given id: " + email));
		    return userMapper.toResponse(user);
	}
	/*
	 * This implemented method is get user Details by using email
	 */
	/*
	 * use Transactional annotation to read data 
	 */
	@Transactional(readOnly = true)
	@Override
	public UserResponse getUserByMobail(String mobaile) {
		/*
		 * Add log
		 */
		log.info("Get user details by using user mobaile numbar {}",mobaile);
		/*
		 * check user dtails in DB
		 */
		User user= userRepository.findByMobileNumber(mobaile).orElseThrow(()->
		         new ResourceNotFoundException("User not found for given mobaile numbar"));
		return userMapper.toResponse(user);
	}
	
	/*
	 * This implemented method is get user Details by using aadhaarNumber
	 */
	@Override
	@Transactional(readOnly = true)
	public UserResponse getUserByAdharNo(String aadhaarNumber) {
		/*
		 * Add Log
		 */
		log.info("get user details by using aadhaarNumber",aadhaarNumber);
		/*
		 * Find data in DB avlible dta to dispaly data and not avlible throw exception
		 */
		User user= userRepository.findByAadhaarNumber(aadhaarNumber).orElseThrow(()->
		     new ResourceNotFoundException("User not found for given aadhaar Number"));
		return userMapper.toResponse(user);
	}
	/*
	 * This implemented method is All get user Details 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> getAllUsers() {
		/*
		 * Add log
		 */
		log.info("");
		return userRepository.findAll().stream()
				.map(userMapper::toResponse)
				.collect(Collectors.toList());
	}
	/*
	 * This implemented method is Soft delete user by using user id
	 */
	@Override
	public ApiResponse deleteUser(Long id) {
		/*
		 * Add Log
		 */
		log.info("soft delete user by using id");
		/*
		 * find data in DB
		 */
		User user= userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"User not found for given id: " + id));
		/*
		 * Set false value
		 */
		user.setActive(false);
		userRepository.save(user);
		log.info("User soft delete with id {}",id);
		return ApiResponse.builder()
				.status(200)
				.message("User deleted successfully")
				.timestamp(LocalDateTime.now())
				.build();
	}

	/*
	 * This implemented method is activateUser by using user id
	 */
	@Override
	public ApiResponse activateUser(Long id) {
		/*
		 * Add log
		 */
		log.info("Activate User by using id {}",id);
		/*
		 * find data in DB
		 */
		User user= userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"User not found for given id: " + id));
		/*
		 * set ststus value true
		 */
		user.setActive(true);
		/*
		 * Save data DB
		 */
		userRepository.save(user);
		log.info("User Activate wit id{}",id);
		
		return ApiResponse.builder()
				.status(200)
				.message("User activate successfully")
				.timestamp(LocalDateTime.now())
				.build();
	}
	/*
	 * This implemented method is DeactivateUser by using user id
	 */
	@Override
	public ApiResponse deActivateUser(Long id) {
		/*
		 * Add Log
		 */
		log.info("soft delete user by using id");
		/*
		 * find data in DB
		 */
		User user= userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"User not found for given id: " + id));
		/*
		 * Set us deActivate
		 */
		user.setActive(false);
		/*
		 * Save data DB
		 */
		userRepository.save(user);
		log.info("User soft deActivate with id {}",id);
		return ApiResponse.builder()
				.status(200)
				.message("User deActivate successfully")
				.timestamp(LocalDateTime.now())
				.build();
	}
	/*
	 * This implemented method is updateUser by using user id
	 */

	@Override
	public UserResponse updateUser(Long id, @Valid UpdateUserRequest request) {
		/*
		 * Add log
		 */

	    log.info("Update user details with user id {}", id);
	    /*
	     * check request is mull or not
	     */

	    if (request == null) {
	        throw new BadRequestException("Update user request cannot be null");
	    }
	    /*
	     * Check data avalibele in DB
	     */

	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found for given id: " + id));

	 /*
	  * Check First  duplicate or not
	  */
	    if (request.getFirstName() != null
	            && !request.getFirstName().equals(user.getFirstName())
	            && userRepository.existsByFirstName(request.getFirstName())) {

	        throw new DuplicateResourceException("First Name is already exists, enter unique");
	    }
	    /*
		   * check Middle Name is duplicate or not
		   */
	    if(request.getMiddleName() !=null
	    		&& !request.getMiddleName().equals(user.getMiddleName())
	    		&& userRepository.existsByMiddleName(request.getMiddleName())) {
	    	 throw new DuplicateResourceException("MiddleName is already exists, enter unique");
	    }
	    /*
		   * check Last Name is duplicate or not
		   */
	    if(request.getLastName() !=null
	    		&& !request.getLastName().equals(user.getLastName())
	    		&& userRepository.existsByLastName(request.getLastName())) {
	    	 throw new DuplicateResourceException("Last Name is already exists, enter unique");
	    }

	  /*
	   * check email is duplicate or not
	   */
	    if (request.getEmail() != null
	            && !request.getEmail().equals(user.getEmail())
	            && userRepository.existsByEmail(request.getEmail())) {

	        throw new DuplicateResourceException("Email is already exists, enter unique");
	    }

	/*
	 * check mobaile number is duplicate or not
	 */
	    if (request.getMobileNumber() != null
	            && !request.getMobileNumber().equals(user.getMobileNumber())
	            && userRepository.existsByMobileNumber(request.getMobileNumber())) {

	        throw new DuplicateResourceException("Mobile number is already exists, enter unique");
	    }

	    /*
	     * Update First Name
	     */
	    if (request.getFirstName() != null) {
	        user.setFirstName(request.getFirstName());
	    }

	    /*
	     * Update Middle name
	     */
	    if(request.getMiddleName() !=null)
	    		user.setMiddleName(request.getMiddleName());
	    /*
	     * Update Last Name
	     */
	    if (request.getLastName() != null) {
	        user.setLastName(request.getLastName());
	    }

	    /*
	     * Update Email
	     */
	    if (request.getEmail() != null) {
	        user.setEmail(request.getEmail());
	    }

	    /*
	     * Update Mobile Number
	     */
	    if (request.getMobileNumber() != null) {
	        user.setMobileNumber(request.getMobileNumber());
	    }

	    User updatedUser = userRepository.save(user);

	    log.info("User details updated successfully with user id {}", id);

	    return userMapper.toResponse(updatedUser);
	}
}