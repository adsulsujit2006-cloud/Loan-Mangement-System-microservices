package com.lms_user_servicess.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms_user_services.service.RoleServices;
import com.lms_user_servicess.dto.request.CreateRoleRequest;
import com.lms_user_servicess.dto.request.UpdateRoleRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.RoleResponse;
import com.lms_user_servicess.exception.DuplicateResourceException;
import com.lms_user_servicess.exception.ResourceNotFoundException;
import com.lms_user_servicess.mapper.RoleMapper;
import com.lms_user_servicess.modal.Role;
import com.lms_user_servicess.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServicesImpl implements RoleServices {
	/*
	 * instance of RoleRepo class
	 */
	@Autowired
	private RoleRepository roleRepository;
	/*
	 * instance of RoleMapper class
	 */
	@Autowired
	private RoleMapper roleMapper;

	/*
	 * This implemented method is create Role
	 */
	@Override
	public RoleResponse createRole(@Valid CreateRoleRequest request) {
		/*
		 * Add log
		 */
		log.info("Creating new Role with Role name {} ", request.getRoleName());

		/*
		 * Check Role duplicate or not and null
		 */
		if (roleRepository.existsByRoleName(request.getRoleName())) {
			throw new DuplicateResourceException("Role alreday exit : " + request.getRoleName());
		}

		/*
		 * Map request to Entity
		 */
		Role role = roleMapper.toEntity(request);
		/*
		 * set Role by default Active
		 */
		role.setActive(true);
		/*
		 * save in DB
		 */

		Role savedRole = roleRepository.save(role);
		log.info("Add Role successfully");
		return roleMapper.toResponse(savedRole);
	}

	/*
	 * This implemented method is update Role details
	 */
	@Override
	public RoleResponse updateRole(Long id, UpdateRoleRequest request) {
		/*
		 * Add log
		 */
		log.info("update role by using id {}", id);
		/*
		 * check role exit in DB and check rull is not equal to null
		 */
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for given id : " + id));

		/*
		 * check description is duplicate or not
		 */
		if (request.getDescription() != null && !request.getDescription().equals(role.getDescription())
				&& roleRepository.existsByDescription(request.getDescription())) {
			throw new DuplicateResourceException("This description already exists,enter unique description");
			
			/*
			 * Set updated description value
			 */
		}
		if(request.getDescription() !=null) {
			role.setDescription(request.getDescription());
		}
		/*
		 * save data in DB
		 */
		Role updateRole= roleRepository.save(role);
		log.info("Role updated successfully");

		return roleMapper.toResponse(updateRole);
	}

	/*
	 * This implemented method is get Role by id
	 */
	@Override
	public RoleResponse getRoleById(Long id) {
		/*
		 * Add log to chek id
		 */
		log.info("Get Role by id {}", id);
		/*
		 * find role DB and not present Role Db then throw exception by using java 8
		 * concept
		 */
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for given id" + id));

		return roleMapper.toResponse(role);
	}

	/*
	 * This implemented method is get All Role
	 */
	@Override
	public List<RoleResponse> getAllRole() {
		/*
		 * add log
		 */
		log.info("Get all role");
		/*
		 * use method chaning and java 8 concepts
		 */

		return roleRepository.findAll().stream().map(roleMapper::toResponse).collect(Collectors.toList());
	}

	/*
	 * This implemented method is delete Role
	 */
	@Override
	public ApiResponse deleteRole(Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for given id: " + id));

		/*
		 * Soft delete only, not delete permanently from DB
		 */
		role.setActive(false);
		/*
		 * save Role in db
		 */
		roleRepository.save(role);

		log.info("Role soft deleted with id {}", id);

		/*
		 * Method chaining
		 */
		return ApiResponse.builder().status(200).message("Role deleted successfully").timestamp(LocalDateTime.now())
				.build();
	}

	/*
	 * This implemented method is active Role
	 */
	@Override
	public ApiResponse ActiveRole(Long id) {
		/*
		 * Add log
		 */
		log.info("Activate Role by using id {} :", id);
		/*
		 * check id db or not
		 */
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for given id: " + id));

		/*
		 * Activate Role
		 */
		role.setActive(true);
		/*
		 * save data in DB
		 */

		roleRepository.save(role);

		log.info("Role Activate  with id {}", id);

		/*
		 * Method chaining
		 */
		return ApiResponse.builder().status(200).message("Role Activate successfully").timestamp(LocalDateTime.now())
				.build();
	}

	/*
	 * update Branch details deActivate Role
	 */
	@Override
	public ApiResponse deActivateRole(Long id) {
		/*
		 * Add log
		 */
		log.info("deActivate Role by using id {} ", id);
		/*
		 * check id in DB
		 */
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for given id: " + id));

		/*
		 * deActivate Role
		 */
		role.setActive(false);
		/*
		 * Save data in DB
		 */

		roleRepository.save(role);

		log.info("Role Deactivate  with id {}", id);

		/*
		 * Method chaining
		 */
		return ApiResponse.builder().status(200).message("Role Deactivate successfully").timestamp(LocalDateTime.now())
				.build();
	}
}
