package com.lms_user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms_user_service.modal.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    boolean existsByBranchCode(String branchCode);

    boolean existsByIfscCode(String ifscCode);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

   // Optional<Branch> findByBranchCode(String branchCode);
}