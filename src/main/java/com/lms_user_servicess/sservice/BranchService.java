package com.lms_user_servicess.sservice;

import java.util.List;

import com.lms_user_servicess.dto.request.CreateBranchRequest;
import com.lms_user_servicess.dto.request.UpdateBranchRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.BranchResponse;

public interface BranchService {
	public BranchResponse createBranch(CreateBranchRequest request);
	
	public BranchResponse updateBranch(Long id, UpdateBranchRequest request);
	
	public BranchResponse getBranchById(Long id);
	
	public List<BranchResponse> getAllBranches();
	
	public ApiResponse deleteBranch(Long id);
	
	public ApiResponse activateBranch(Long id);
	
	public ApiResponse deActivateBranch(Long id);

}
