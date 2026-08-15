package com.lms_user_servicess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms_user_servicess.enums.RoleType;
import com.lms_user_servicess.modal.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleName(RoleType roleName);

    Optional<Role> findByRoleName(RoleType roleType);

}