package com.lms_user_servicess.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms_user_servicess.dto.request.CreateRoleRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.RoleResponse;
import com.lms_user_servicess.sservice.RoleServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@Slf4j
public class Rolentroller {

	private final RoleServices roleServices;

	/*
	 * REST API : Register Role with required details
	 */
	@PostMapping
	public ResponseEntity<RoleResponse> createBranch(@Valid @RequestBody CreateRoleRequest request) {

		log.info("REST Request: Create Branch");

		RoleResponse roleResponce = roleServices.createRole(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(roleResponce);
	}
	@GetMapping("/{id}")
	public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id){
		log.info("REST Request : Get Role by id {}",id);
		return ResponseEntity.ok(roleServices.getRoleById(id));
	}
	@GetMapping
	public ResponseEntity<List<RoleResponse>> getAllRole(){
		return ResponseEntity.ok(roleServices.getAllRole());
	}
	
	/*
	 * REST API : Soft delete Role by using Role id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteRole(@PathVariable Long id) {

		log.info("REST Request : Delete Role {}", id);

		return ResponseEntity.ok(roleServices.deleteRole(id));
	}
	/*
	 * REST API : Soft Activate Role by using Role id
	 */
	@PatchMapping("/{id}/activate")
	public ResponseEntity<ApiResponse> activateRole(@PathVariable Long id) {

		log.info("REST Request : Activate Role {}", id);

		return ResponseEntity.ok(roleServices.ActiveRole(id));
	}
	@PatchMapping("/{id}/deActivate")
	public ResponseEntity<ApiResponse> deActivateRole(@PathVariable Long id) {

		log.info("REST Request : Deactivate Role {}", id);

		return ResponseEntity.ok(roleServices.deActivateRole(id));
	}
}
	
