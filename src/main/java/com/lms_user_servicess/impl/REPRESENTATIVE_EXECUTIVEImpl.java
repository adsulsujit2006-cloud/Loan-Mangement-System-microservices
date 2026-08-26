package com.lms_user_servicess.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms_user_services.service.REPRESENTATIVE_EXECUTIVE;
import com.lms_user_services.service.UserServices;
import com.lms_user_servicess.dto.request.CreateBranchRequest;
import com.lms_user_servicess.dto.request.LoginRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.LoginResponse;
import com.lms_user_servicess.dto.responce.UserResponse;
import com.lms_user_servicess.exception.BadRequestException;
import com.lms_user_servicess.mapper.UserMapper;
import com.lms_user_servicess.modal.User;
import com.lms_user_servicess.repository.BranchRepository;
import com.lms_user_servicess.repository.RoleRepository;
import com.lms_user_servicess.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class REPRESENTATIVE_EXECUTIVEImpl implements REPRESENTATIVE_EXECUTIVE {

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
     * instance of UserServices
     */
    @Autowired
    private UserServices userServices;

    /*
     * This implemented method is Register Representative Executive user
     */
    @Override
    public UserResponse registorRE(UserRegistrationRequest request) {

        /*
         * Check request
         */
        if (request == null) {
            throw new IllegalArgumentException(
                    "User registration request cannot be null");
        }

        /*
         * Add log
         */
        log.info("Registering Representative Executive user");

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
     * This implemented method is Register user
     */
    @Override
    public UserResponse registorUser(CreateBranchRequest request) {

        // TODO: Implement branch user registration
        return null;
    }

	@Override
	public LoginResponse RElogin(LoginRequest request) {
		/*
		 * Add log
		 */
		log.info("Login REPRESENTATIVE_EXECUTIVE {}",request);
		User users = new User();
		
		LoginResponse user= userServices.login(request);
		//users.setLoginref(request.getUsername());
		return null;
	}

	@Override
	public LoginResponse userlogin(LoginRequest request) {

	    /*
	     * Add log
	     */
	    log.info("Login REPRESENTATIVE_EXECUTIVE username: {}",request);

	    /*
	     * Check request
	     */
	    if (request == null) {
	        throw new BadRequestException("Please enter login information.");
	    }

	    /*
	     * Call common user login service
	     */
	    LoginResponse response = userServices.login(request);

	    /*
	     * Set login reference
	     */
	    response.setLoginref(request.getUsername());

	    /*
	     * Return login response
	     */
	    return response;
	}
	}