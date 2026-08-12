package com.lms_user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms_user_service.domain.PermissionType;
import com.lms_user_service.modal.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
	 boolean existsByPermissionName(PermissionType permissionName);

	    Optional<Permission> findByPermissionName(PermissionType permissionName);

}
