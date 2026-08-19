package com.lms_user_servicess.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms_user_servicess.dto.request.CreateBranchRequest;
import com.lms_user_servicess.dto.request.UpdateBranchRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.BranchResponse;
import com.lms_user_servicess.exception.BadRequestException;
import com.lms_user_servicess.exception.DuplicateResourceException;
import com.lms_user_servicess.exception.ResourceNotFoundException;
import com.lms_user_servicess.mapper.BranchMapper;
import com.lms_user_servicess.modal.Branch;
import com.lms_user_servicess.repository.BranchRepository;
import com.lms_user_servicess.sservice.BranchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BranchServiceimpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private BranchMapper branchMapper;

	@Override
	public BranchResponse createBranch(CreateBranchRequest request) {

		/*
		 * Check request is null or not
		 */
		if (request == null) {
			throw new BadRequestException("Branch request cannot be null");
		}

		log.info("Creating new Branch with branch code {}", request.getBranchCode());

		/*
		 * Check branch code already exists or not
		 */
		if (branchRepository.existsByBranchCode(request.getBranchCode())) {
			throw new DuplicateResourceException("Branch code already exists");
		}

		log.info("Creating new Branch with branch name {}", request.getBranchName());

		/*
		 * Check branch name already exists or not
		 */
		if (branchRepository.existsByBranchName(request.getBranchName())) {
			throw new DuplicateResourceException("Branch name already exists");
		}

		log.info("Creating new Branch with branch ifscCode {}", request.getIfscCode());

		/*
		 * Check IFSC code already exists or not
		 */
		if (branchRepository.existsByIfscCode(request.getIfscCode())) {
			throw new DuplicateResourceException("IFSC code already exists");
		}

		log.info("Creating new Branch with branch email {}", request.getEmail());

		/*
		 * Check email already exists or not
		 */
		if (branchRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException("Branch email already exists");
		}

		log.info("Creating new Branch with branch phoneNumber {}", request.getPhoneNumber());

		/*
		 * Check phone number already exists or not
		 */
		if (branchRepository.existsByPhoneNumber(request.getPhoneNumber())) {
			throw new DuplicateResourceException("Branch mobile number already exists");
		}

		/*
		 * Map request to Entity
		 */
		Branch branch = branchMapper.toEntity(request);

		/*
		 * Set branch by default active true
		 */
		branch.setActive(true);

		/*
		 * Save data in database
		 */
		Branch newBranch = branchRepository.save(branch);

		/*
		 * Add log for create branch successful
		 */
		log.info("Branch created successfully with branch code {}", newBranch.getBranchCode());

		return branchMapper.toResponse(newBranch);
	}

	/*
	 * This implemented method is update Branch details
	 */
	@Override
	public BranchResponse updateBranch(Long id, UpdateBranchRequest request) {

		log.info("Update branch details with branch id {}", id);

		if (id == null) {
			throw new BadRequestException("Branch id cannot be null");
		}

		if (request == null) {
			throw new BadRequestException("Branch request cannot be null");
		}

		/*
		 * Find branch exists or not
		 */
		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Branch not found for given id: " + id));

		/*
		 * Check IFSC code duplicate or not
		 */
		if (request.getIfscCode() != null
				&& !request.getIfscCode().equals(branch.getIfscCode())
				&& branchRepository.existsByIfscCode(request.getIfscCode())) {

			throw new DuplicateResourceException(
					"IFSC code already exists, enter unique IFSC code");
		}

		/*
		 * Check email duplicate or not
		 */
		if (request.getEmail() != null
				&& !request.getEmail().equals(branch.getEmail())
				&& branchRepository.existsByEmail(request.getEmail())) {

			throw new DuplicateResourceException(
					"Email already exists, enter unique email");
		}

		/*
		 * Check phone number duplicate or not
		 */
		if (request.getPhoneNumber() != null
				&& !request.getPhoneNumber().equals(branch.getPhoneNumber())
				&& branchRepository.existsByPhoneNumber(request.getPhoneNumber())) {

			throw new DuplicateResourceException(
					"Phone number already exists, enter new phone number");
		}

		/*
		 * Set updated values
		 */
		if (request.getIfscCode() != null) {
			branch.setIfscCode(request.getIfscCode());
		}

		if (request.getEmail() != null) {
			branch.setEmail(request.getEmail());
		}

		if (request.getPhoneNumber() != null) {
			branch.setPhoneNumber(request.getPhoneNumber());
		}

		Branch updatedBranch = branchRepository.save(branch);

		log.info("Branch updated successfully with id {}", id);

		return branchMapper.toResponse(updatedBranch);
	}

	@Override
	public BranchResponse getBranchById(Long id) {

		log.info("Get Branch by id {}", id);

		if (id == null) {
			throw new BadRequestException("Branch id cannot be null");
		}

		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Bank branch not found for given id: " + id));

		log.info("Get branch data successfully with branch id {}", id);

		return branchMapper.toResponse(branch);
	}

	/*
	 * This implemented method is get all Branches details
	 */
	@Override
	public List<BranchResponse> getAllBranches() {

		log.info("Get all branch details");

		/*
		 * Java 8 method reference
		 */
		return branchRepository.findAll()
				.stream()
				.map(branchMapper::toResponse)
				.collect(Collectors.toList());
	}

	/*
	 * This method implements delete Branch with id
	 */
	@Override
	public ApiResponse deleteBranch(Long id) {

		if (id == null) {
			throw new BadRequestException("Branch id cannot be null");
		}

		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Bank branch not found for given id: " + id));

		/*
		 * Soft delete only, not delete permanently from DB
		 */
		branch.setActive(false);

		branchRepository.save(branch);

		log.info("Branch soft deleted with id {}", id);

		/*
		 * Method chaining
		 */
		return ApiResponse.builder()
				.status(200)
				.message("Branch deleted successfully")
				.timestamp(LocalDateTime.now())
				.build();
	}

	/*
	 * This method implements activate Branch with id
	 */
	@Override
	public ApiResponse activateBranch(Long id) {

		if (id == null) {
			throw new BadRequestException("Branch id cannot be null");
		}

		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Bank branch not found for given id: " + id));

		branch.setActive(true);

		branchRepository.save(branch);

		log.info("Branch activated with id {}", id);

		return ApiResponse.builder()
				.status(200)
				.message("Branch activated successfully")
				.timestamp(LocalDateTime.now())
				.build();
	}

	/*
	 * This method implements deactivate Branch with id
	 */
	@Override
	public ApiResponse deActivateBranch(Long id) {

		if (id == null) {
			throw new BadRequestException("Branch id cannot be null");
		}

		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Bank branch not found for given id: " + id));

		branch.setActive(false);

		branchRepository.save(branch);

		log.info("Branch deactivated with id {}", id);

		/*
		 * Method chaining
		 */
		return ApiResponse.builder()
				.status(200)
				.message("Branch deactivated successfully")
				.timestamp(LocalDateTime.now())
				.build();
	}
}