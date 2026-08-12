package com.lms_user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms_user_service.domain.RoleType;
import com.lms_user_service.modal.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
	
    boolean existsByRoleName(RoleType roleName);

    Optional<Role> findByRoleName(RoleType roleName);

}
