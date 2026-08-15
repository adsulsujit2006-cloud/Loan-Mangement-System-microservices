package com.lms_user_servicess.impl;

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
		log.info("Branch created successfully with branch code: {}", newBranch.getBranchCode());
		return branchMapper.toResponse(newBranch);
	}
	/*
	 * This impleted method is get BrachDetails by id
	 */

	@Override
	public BranchResponse getBranchById(Long id) {
		log.info("get Branch by id {}", id);

		Branch branch = branchRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank branch not found for given id: " + id));
		log.info("get data successfully with branch id {} ",branch);
		return branchMapper.toResponse(branch);
	}

	@Override
	public List<BranchResponse> getAllBranches() {
	    log.info("Get all branch details : ");
	    return branchRepository.findAll()
	            .stream()
	            .map(branchMapper::toResponse)
	            .collect(Collectors.toList());
	}

	@Override
	public ApiResponse deleteBranch(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse deactivateBranch(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BranchResponse updateBranch(Long id, UpdateBranchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
