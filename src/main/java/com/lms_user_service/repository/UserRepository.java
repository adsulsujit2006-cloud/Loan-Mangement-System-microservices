package com.lms_user_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms_user_service.domain.RoleType;
import com.lms_user_service.modal.User;

@Repository
public interface UserRepository {
	Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumber(String mobileNumber);

    Optional<User> findByCustomerCode(String customerCode);

    Optional<User> findByPanNumber(String panNumber);

    Optional<User> findByAadhaarNumber(String aadhaarNumber);

    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByPanNumber(String panNumber);

    boolean existsByAadhaarNumber(String aadhaarNumber);

    @Query(
    	    "SELECT DISTINCT u " +
    	    "FROM User u " +
    	    "JOIN u.roles r " +
    	    "WHERE u.branch.id = :branchId " +
    	    "AND r.roleName = :roleName " +
    	    "AND u.active = true"
    	)
    	List<User> findActiveUsersByBranchAndRole(
    	        @Param("branchId") Long branchId,
    	        @Param("roleName") RoleType roleName
    	);


}
