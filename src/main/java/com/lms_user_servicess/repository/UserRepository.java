package com.lms_user_servicess.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms_user_servicess.enums.RoleType;
import com.lms_user_servicess.modal.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumber(String mobileNumber);

    Optional<User> findByCustomerCode(String customerCode);

    Optional<User> findByPanNumber(String panNumber);

    Optional<User> findByAadhaarNumber(String aadhaarNumber);
    boolean existsByFirstName(String firstName);

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
