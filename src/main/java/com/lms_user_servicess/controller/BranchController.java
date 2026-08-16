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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms_user_servicess.dto.request.CreateBranchRequest;
import com.lms_user_servicess.dto.request.UpdateBranchRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.BranchResponse;
import com.lms_user_servicess.sservice.BranchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
@Slf4j
public class BranchController {

	private final BranchService branchService;

	/*
	 * REST API : Register bank branch with required details
	 */
	@PostMapping("/register")
	public ResponseEntity<BranchResponse> createBranch(@Valid @RequestBody CreateBranchRequest request) {

		log.info("REST Request: Create Branch");

		BranchResponse response = branchService.createBranch(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/*
	 * REST API : Get the details of a bank branch using its branch ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BranchResponse> getBranchById(@PathVariable Long id) {

		log.info("REST Request : Get branch by id {}", id);

		return ResponseEntity.ok(branchService.getBranchById(id));
	}

	/*
	 * REST API : Get all bank branch details
	 */
	@GetMapping
	public ResponseEntity<List<BranchResponse>> getAllBranches() {

		log.info("REST Request : Get All Branches");

		return ResponseEntity.ok(branchService.getAllBranches());
	}

	/*
	 * REST API : Soft delete branch by using branch id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) {

		log.info("REST Request : Delete branch {}", id);

		return ResponseEntity.ok(branchService.deleteBranch(id));
	}

	/*
	 * REST API : Activate branch by using branch id
	 */
	@PatchMapping("/{id}/activat")
	public ResponseEntity<ApiResponse> activateBranch(@PathVariable Long id) {
		log.info("REST Request : Activate branch {}", id);
		return ResponseEntity.ok(branchService.activateBranch(id));
	}

	/*
	 * REST API : Deactivate branch by using branch id
	 */
	@PatchMapping("/{id}/deactivat")
	public ResponseEntity<ApiResponse> deactivateBranch(@PathVariable Long id) {
		log.info("REST Request : deActivate branch {}", id);
		return ResponseEntity.ok(branchService.activateBranch(id));
	}
/*
 * REST API : Update branch details using branch id
 */
	@PutMapping("/{id}")
	public ResponseEntity<BranchResponse> updateBranch(@PathVariable Long id,
			@Valid @RequestBody UpdateBranchRequest request) {

		log.info("REST Request : Update Branch {}", id);

		return ResponseEntity.ok(branchService.updateBranch(id, request));
	}

}