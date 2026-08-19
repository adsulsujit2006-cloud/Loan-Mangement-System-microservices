package com.lms_user_servicess.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms_user_servicess.dto.request.CreateRoleRequest;
import com.lms_user_servicess.dto.request.UpdateRoleRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.RoleResponse;
import com.lms_user_servicess.exception.BadRequestException;
import com.lms_user_servicess.exception.DuplicateResourceException;
import com.lms_user_servicess.exception.ResourceNotFoundException;
import com.lms_user_servicess.mapper.RoleMapper;
import com.lms_user_servicess.modal.Branch;
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
	public RoleResponse createRole(@Valid CreateRoleRequest request) {
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
		/*
		 * set Role by default Active
		 */
		role.setActive(true);
		
		Role savedRole= roleRepository.save(role);
		log.info("Add Role successfully");		
		return roleMapper.toResponse(savedRole);
	}

	@Override
	public RoleResponse updateRole(Long id, UpdateRoleRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
//	  /*
//     * Check id is null or not
//     */
//    if (id == null) {
//        throw new BadRequestException("Enter the appropriate ID");
//    }
//
//    log.info("Get permission by id {}", id);
//
//    /*
//     * Find the permission id in DB
//     */
//    Permission permission = permissionRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Permission not found for given id: " + id));
//
//    return permissionMapper.toResponse(permission);
//}
//
///*
// * This method implements activate Permission with id
// */
//
//	@Override
	public RoleResponse getRoleById(Long id) {
		/*
		 * Add log to chek id 
		 */
		log.info("Get Role by id {}",id);
		/*
		 * find role DB and not present Role Db then throw exception by using java 8 concept
		 */
		Role role= roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role not found for given id"+id));
		
		return roleMapper.toResponse(role);
	}
	@Override
	public List<RoleResponse> getAllRole() {

		log.info("Get all role");

		return roleRepository.findAll()
				.stream()
				.map(roleMapper::toResponse)
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse deleteRole(Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Role not found for given id: " + id));

		/*
		 * Soft delete only, not delete permanently from DB
		 */
		role.setActive(false);

		roleRepository.save(role);

		log.info("Role soft deleted with id {}", id);

		/*
		 * Method chaining 
		 */
		return ApiResponse.builder().status(200).message("Role deleted successfully").timestamp(LocalDateTime.now()).build();
	}

	@Override
	public ApiResponse ActiveRole(Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Role not found for given id: " + id));

		/*
		 * Activate Role
		 */
		role.setActive(true);

		roleRepository.save(role);

		log.info("Role Activate  with id {}", id);

		/*
		 * Method chaining 
		 */
		return ApiResponse.builder().status(200).message("Role Activate successfully").timestamp(LocalDateTime.now()).build();
	}

	@Override
	public ApiResponse deActivateRole(Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Role not found for given id: " + id));

		/*
		 * deActivate Role
		 */
		role.setActive(false);

		roleRepository.save(role);

		log.info("Role Deactivate  with id {}", id);

		/*
		 * Method chaining 
		 */
		return ApiResponse.builder().status(200).message("Role Deactivate successfully").timestamp(LocalDateTime.now()).build();
	}
	}


