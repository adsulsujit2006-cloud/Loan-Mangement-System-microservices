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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms_user_servicess.dto.request.CreatePermissionRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.PermissionResponse;
import com.lms_user_servicess.sservice.PermissionServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
@Slf4j
public class PermissionController {

	@Autowired
	private PermissionServices permissionService;

	/*
	 * REST API : create the permission
	 */
	@PostMapping
	public ResponseEntity<PermissionResponse> addPermission(@Valid @RequestBody CreatePermissionRequest request) {
		log.info("REST Request: Create permission");

		PermissionResponse response = permissionService.addPermission(request);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/*
	 * REST API : get permission by using id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PermissionResponse> getPermissionById(@PathVariable Long id) {
		log.info("REST Request: get permission ny id");

		PermissionResponse response = permissionService.getPermissionById(id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * REST API : Get allPermission details 
	 */
	@GetMapping
	public ResponseEntity<List<PermissionResponse>> getAllPermission() {

		log.info("REST Request : Get All Branches");

		return ResponseEntity.ok(permissionService.getAllPermissions());
	}

	/*
	 * REST API : Deactivate Permission by using Permission id
	 */
	@PatchMapping("/{id}/deactivat")
	public ResponseEntity<ApiResponse> deactivatePermission(@PathVariable Long id) {
		
		log.info("REST Request : deActivate Permission {}", id);
		
		return ResponseEntity.ok(permissionService.deActivatePermission(id));
	}
	/*
	 * REST API : Soft delete branch by using Permission id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deletePermission(@PathVariable Long id) {

		log.info("REST Request : Delete Permission {}", id);

		return ResponseEntity.ok(permissionService.deletePermission(id));
	}
	
	/*
	 * REST API : Activate Permission by using Permission id
	 */
	@PatchMapping("/{id}/activat")
	public ResponseEntity<ApiResponse> activateBranch(@PathVariable Long id) {
		log.info("REST Request : Activate permission {}", id);
		return ResponseEntity.ok(permissionService.activatePermission(id));
	}

}
