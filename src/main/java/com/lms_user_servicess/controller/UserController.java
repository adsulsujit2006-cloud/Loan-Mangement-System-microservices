package com.lms_user_servicess.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms_user_services.service.UserServices;
import com.lms_user_servicess.dto.request.UpdateUserRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.UserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	@Autowired
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
		/*
		 * Add log
		 */
		log.info("Rest Request : Get user by email {}", email);
		return ResponseEntity.ok(userServices.getUserByEmail(email));
	}
	/*
	 * REST API : Get the details of a user using its user email
	 */
	@GetMapping("/phonenumber/{phonenumber}")
	public ResponseEntity<UserResponse> getUserByPhonenumber(
			@PathVariable("phonenumber") String phonenumber) {

		/*
		 * Add log
		 */
		log.info("Rest Request : Get user by phone number {}", phonenumber);

		return ResponseEntity.ok(userServices.getUserByMobail(phonenumber)
		);
	}
	@GetMapping("/aadhaarNumber/{phonenumber}")
	public ResponseEntity<UserResponse> getUserByAadhaarNumber(
			@PathVariable("phonenumber") String aadhaarNumber) {

		/*
		 * Add log
		 */
		log.info("Rest Request : Get user by aadhaar Number {}", aadhaarNumber);

		return ResponseEntity.ok(userServices.getUserByAdharNo(aadhaarNumber));
	}
	/*
	 * REST API : Get all User details
	 */
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {

		log.info("REST Request : Get All Branches");

		return ResponseEntity.ok(userServices.getAllUsers());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
		log.info("REST Request : Delete user with id {}",id);
		
		return ResponseEntity.ok(userServices.deleteUser(id));
	}
	@PatchMapping("/{id}/activate")
	public ResponseEntity<ApiResponse> activateUSer(@PathVariable Long id){
		log.info("REST Request : Activate user with id {}",id);
		return ResponseEntity.ok(userServices.activateUser(id));
	}
	@PatchMapping("/{id}/deactivate")
	public ResponseEntity<ApiResponse> deActivateUser(@PathVariable Long id) {

	    log.info("REST Request : Deactivate User with id {}", id);

	    return ResponseEntity.ok(userServices.deActivateUser(id));
	}
	/*
	 * REST API : Update branch details using branch id
	 */
		@PutMapping("/{id}")
		public ResponseEntity<UserResponse> updateUserh(@PathVariable Long id,
				 @RequestBody UpdateUserRequest request) {

			log.info("REST Request : Update Branch {}", id);

			return ResponseEntity.ok(userServices.updateUser(id, request));
		}

}
	
	
	