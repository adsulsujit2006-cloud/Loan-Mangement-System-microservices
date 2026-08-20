package com.lms_user_servicess.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms_user_services.service.PermissionServices;
import com.lms_user_servicess.dto.request.CreatePermissionRequest;
import com.lms_user_servicess.dto.responce.ApiResponse;
import com.lms_user_servicess.dto.responce.PermissionResponse;
import com.lms_user_servicess.exception.BadRequestException;
import com.lms_user_servicess.exception.DuplicateResourceException;
import com.lms_user_servicess.exception.ResourceNotFoundException;
import com.lms_user_servicess.mapper.PermissionMapper;
import com.lms_user_servicess.modal.Permission;
import com.lms_user_servicess.repository.PermissionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PermissionServicesImpl implements PermissionServices {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PermissionResponse addPermission(@Valid CreatePermissionRequest request) {

        /*
         * Check permission request is null or not
         */
        if (request == null) {
            throw new BadRequestException("Permission request cannot be null");
        }

        /*
         * Check permission duplicate
         */
        if (permissionRepository.existsByPermissionName(request.getPermissionName())) {
            throw new DuplicateResourceException("Permission already exists: " + request.getPermissionName());
        }

        /*
         * Convert Map request to entity
         */
        Permission permission = permissionMapper.toEntity(request);

        /*
         * Set permission default status is true
         */
        permission.setActive(true);

        /*
         * Save permission in DB
         */
        Permission savedPermission = permissionRepository.save(permission);

        log.info("Add Permission successfully");

        return permissionMapper.toResponse(savedPermission);
    }

    /*
     * This method implements getPermissionByID
     */
    @Override
    public PermissionResponse getPermissionById(Long id) {

        /*
         * Check id is null or not
         */
        if (id == null) {
            throw new BadRequestException("Enter the appropriate ID");
        }

        log.info("Get permission by id {}", id);

        /*
         * Find the permission id in DB
         */
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found for given id: " + id));

        return permissionMapper.toResponse(permission);
    }

    /*
     * This method implements activate Permission with id
     */
    @Override
    public ApiResponse activatePermission(Long id) {

        /*
         * Check id is null or not
         */
        if (id == null) {
            throw new BadRequestException("Enter the appropriate ID");
        }

        /*
         * Find permission by id
         */
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found for given id: " + id));

        /*
         * Activate permission
         */
        permission.setActive(true);
        permissionRepository.save(permission);

        log.info("Permission activated successfully with id {}", id);

        /*
         * Use Method chaining concept
         */
        return ApiResponse.builder().status(200).message("Permission activated successfully").timestamp(LocalDateTime.now()).build();
    }

    /*
     * This method implements deActivate Permission with id
     */
    @Override
    public ApiResponse deActivatePermission(Long id) {

        /*
         * Check id is null or not
         */
        if (id == null) {
            throw new BadRequestException("Enter the appropriate ID");
        }

        /*
         * Find permission by id
         */
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found for given id: " + id));

        /*
         * Deactivate permission
         */
        permission.setActive(false);
        permissionRepository.save(permission);

        log.info("Permission deactivated successfully with id {}", id);

        /*
         * Use Method chaining concept
         */
        return ApiResponse.builder().status(200).message("Permission deactivated successfully").timestamp(LocalDateTime.now()).build();
    }

    /*
     * This method implements delete Permission with id
     */
    @Override
    public ApiResponse deletePermission(Long id) {

        /*
         * Check id is null or not
         */
        if (id == null) {
            throw new BadRequestException("Enter the appropriate ID");
        }

        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found for given id: " + id));

        /*
         * Soft Delete only, not delete permanently in DB
         */
        permission.setActive(false);
        permissionRepository.save(permission);

        log.info("Permission soft deleted with id {}", id);

        /*
         * Use Method chaining concept
         */
        return ApiResponse.builder().status(200).message("Permission deleted successfully").timestamp(LocalDateTime.now()).build();
    }

    /*
     * This Implemented Method Is Get All Permission Details
     */
    @Override
    public List<PermissionResponse> getAllPermissions() {

        log.info("Get all permissions");

        /*
         * Java 8 method reference calling
         */
        return permissionRepository.findAll().stream().map(permissionMapper::toResponse).collect(Collectors.toList());
    }
}