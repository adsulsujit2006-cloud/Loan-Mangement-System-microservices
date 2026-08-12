package com.lms_user_service.mapper;

import org.mapstruct.Mapper;

import com.lms_user_service.dto.request.CreateBranchRequest;
import com.lms_user_service.dto.request.UpdateBranchRequest;
import com.lms_user_service.dto.responce.BranchResponse;
import com.lms_user_service.modal.Branch;


@Mapper(componentModel = "spring")
public interface BranchMapper {
	 Branch toEntity(CreateBranchRequest request);

	    Branch toEntity(UpdateBranchRequest request);

	    BranchResponse toResponse(Branch branch);


}
