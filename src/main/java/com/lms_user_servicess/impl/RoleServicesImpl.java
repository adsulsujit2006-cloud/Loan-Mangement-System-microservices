package com.lms_user_servicess.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms_user_servicess.dto.request.CreateRoleRequest;
import com.lms_user_servicess.dto.request.UpdateRoleRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.RoleResponse;
import com.lms_user_servicess.exception.BadRequestException;
import com.lms_user_servicess.exception.DuplicateResourceException;
import com.lms_user_servicess.mapper.RoleMapper;
import com.lms_user_servicess.modal.Permission;
import com.lms_user_servicess.modal.Role;
import com.lms_user_servicess.repository.RoleRepository;
import com.lms_user_servicess.sservice.RoleServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServicesImpl implements RoleServices{
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public RoleResponse createRole(CreateRoleRequest request) {
		 /*
         * Check permission request is null or not
         */
        if (request == null) {
            throw new BadRequestException("Permission request cannot be null");
        }
      //  log.info("Creating new Branch with branch code {}", request.getBranchCode())
		/*
		 * Add log 
		 */
		log.info("Creating new Role with Role name {} ",request.getRoleName());
		
		 /*
        * Check Role duplicate
        */
		if(roleRepository.existsByRoleName(request.getRoleName())) {
			throw new DuplicateResourceException("Role alreday exit : "+request.getRoleName());
		}
		
		/*
		 * Map request to Entity
		 */
		Role role= roleMapper.toEntity(request);
		
		role.setActive(true);
		Role savedRole= roleRepository.save(role);
		log.info("Add Role successfully");
//		Permission savedPermission = permissionRepository.save(permission);
//
//        log.info("Add Permission successfully");
//
//        return permissionMapper.toResponse(savedPermission);

		
		
		return roleMapper.toResponse(savedRole);
	}

	@Override
	public RoleResponse updateRole(Long id, UpdateRoleRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleResponse getRoleById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleResponse> getAllRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse deleteRole(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse ActiveRole(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse deActivateRole(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
