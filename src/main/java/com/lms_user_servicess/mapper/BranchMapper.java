package com.lms_user_servicess.mapper;

import org.mapstruct.Mapper;

import com.lms_user_servicess.dto.request.CreateBranchRequest;
import com.lms_user_servicess.dto.request.UpdateBranchRequest;
import com.lms_user_servicess.dto.responce.BranchResponse;
import com.lms_user_servicess.modal.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    Branch toEntity(CreateBranchRequest request);

    Branch toEntity(UpdateBranchRequest request);

    BranchResponse toResponse(Branch branch);
}