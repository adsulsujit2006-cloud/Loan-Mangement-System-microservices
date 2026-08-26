package com.lms_user_servicess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms_user_servicess.modal.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    boolean existsByBranchCode(String branchCode);

    boolean existsByBranchName(String branchName);

    boolean existsByIfscCode(String ifscCode);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}