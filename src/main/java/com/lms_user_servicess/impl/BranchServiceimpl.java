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

		log.info("Creating new Branch with branch code {}", request.getBranchCode());
		/*
		 * to check branch code alreday exit or not
		 */
		if (branchRepository.existsByBranchCode(request.getBranchCode())) {
			throw new DuplicateResourceException("Branch code already exists");
		}

		log.info("Creating new Branch with branch name {}", request.getBranchName());
		/*
		 * to check branch name alreday exit or not
		 */

		if (branchRepository.existsByBranchName(request.getBranchName())) {
			throw new DuplicateResourceException("Branch name is already exists");
		}

		log.info("Creating new Branch with branch ifscCode {}", request.getIfscCode());
		/*
		 * to check ifsc code alreday exit or not
		 */

		if (branchRepository.existsByIfscCode(request.getIfscCode())) {
			throw new DuplicateResourceException("IFSC code is already exists");
		}

		log.info("Creating new Branch with branch email {}", request.getEmail());
		/*
		 * to check email ic exit or not
		 */

		if (branchRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException("Branch email is already exists");
		}
		log.info("Creating new Branch with branch phoneNumber {}", request.getPhoneNumber());
		/*
		 * to check phone number is exit or not
		 */

		if (branchRepository.existsByPhoneNumber(request.getPhoneNumber())) {
			throw new DuplicateResourceException("Branch mobile number is already exists");
		}

		// Map request to Entity
		Branch branch = branchMapper.toEntity(request);

		/*
		 * set branch bydefault active true
		 */
		branch.setActive(true);

		// Data Save in dataBase
		Branch newBranch = branchRepository.save(branch);

		// add log for create branch successful
		log.info("Branch created successfully with branch code: {}");
		return branchMapper.toResponse(newBranch);
	}

	/*
	 * This impleted method is get BrachDetails by id
	 */
	@Override
	public BranchResponse updateBranch(Long id, UpdateBranchRequest request) {
		log.info("Update branch details with branch id {}", id);
		/*
		 * find branch exit or not
		 */
		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Branch not found given id" + id));

		/*
		 * check ifsc code duplicate or not and null
		 */
		if (branch.getIfscCode() != null && branchRepository.existsByIfscCode(request.getIfscCode())) {
			throw new DuplicateResourceException("ifsc code alreday exit enter unique ifsc code");
		}
		/*
		 * check email duplicate or not and null
		 */
		if (branch.getEmail() != null && branchRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException("Email alreday exit enter unique Email");
		}
		/*
		 * check phone number duplicate or not and null
		 */
		if (branch.getPhoneNumber() != null && branchRepository.existsByPhoneNumber(request.getPhoneNumber())) {
			throw new DuplicateResourceException("Phone number is slreday exit enter new phone number");
		}
		/*
		 * Set updated value
		 */
		branch.setIfscCode(request.getIfscCode());
		branch.setEmail(request.getEmail());
		branch.setPhoneNumber(request.getPhoneNumber());

		Branch updatedBranch = branchRepository.save(branch);
		log.info("Branch updated successfully {}");
		return branchMapper.toResponse(updatedBranch);
	}

	@Override
	public BranchResponse getBranchById(Long id) {
		log.info("get Branch by id {}", id);

		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank branch not found for given id: " + id));
		log.info("get data successfully with branch id {} ", branch);
		return branchMapper.toResponse(branch);
	}
	/*
	 * This Impleted Method Is Get All Branches Details
	 */

	@Override
	public List<BranchResponse> getAllBranches() {
		log.info("Get all branch details : ");
		/*
		 * use java 8 reference method callicing concept
		 */
		return branchRepository.findAll().stream().map(branchMapper::toResponse).collect(Collectors.toList());
	}

	/*
	 * This method implements delete Branch with id
	 */
	@Override
	public ApiResponse deleteBranch(Long id) {

		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank branch not found for given id: " + id));

		/*
		 * Soft Delete only, not delete permanently in DB
		 */
		branch.setActive(false);
		branchRepository.save(branch);

		log.info("Branch soft delete with id {}", id);
		/*
		 * use method chaning concept
		 */
		return ApiResponse.builder().status(200).message("Branch delete successfully").timestamp(LocalDateTime.now())
				.build();
	}

	/*
	 * This method implements Acitivate Branch with id
	 */
	@Override
	public ApiResponse activateBranch(Long id) {
		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank branch not found for given id: " + id));
		branch.setActive(true);
		branchRepository.save(branch);
		log.info("Branch activate with id {}", id);
		return ApiResponse.builder().status(200).message("Branch Activate successfully").timestamp(LocalDateTime.now())
				.build();
	}

	/*
	 * This method implements diactivate Branch with id
	 */

	@Override
	public ApiResponse deActivateBranch(Long id) {
		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank branch not found for given id: " + id));

		branch.setActive(false);
		branchRepository.save(branch);

		log.info("Deactivate branch with id {}", id);
		/*
		 * use method chaning concept
		 */
		return ApiResponse.builder().status(200).message("Branch delete successfully").timestamp(LocalDateTime.now())
				.build();
	}

}
