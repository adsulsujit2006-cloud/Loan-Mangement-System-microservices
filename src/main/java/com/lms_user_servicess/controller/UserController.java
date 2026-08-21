package com.lms_user_servicess.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms_user_services.service.UserServices;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserServices userServices;

	/*
	 * REST API : Register bank branch with required details
	 */
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRegistrationRequest request){
		log.info("REST Request : create user");
		UserResponse response = userServices.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/*
	 * REST API : Get the details of a user using its user ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
		log.info("Rest Request : Get user by id {}", id);
		return ResponseEntity.ok(userServices.getUserById(id));
	}

	/*
	 * REST API : Get the details of a user using its user email
	 */
	@GetMapping("/email/{email}")
	public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
		log.info("Rest Request : Get user by email {}", email);
		return ResponseEntity.ok(userServices.getUserByEmail(email));
	}

}
	
	
	
//	@GetMapping("/{id}")
//	public ResponseEntity<BranchResponse> getBranchById(@PathVariable Long id) {
//
//		log.info("REST Request : Get branch by id {}", id);
//
//		return ResponseEntity.ok(branchService.getBranchById(id));
//	}



