package com.lms_user_servicess.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms_user_services.service.Branch_ManagerService;
import com.lms_user_servicess.dto.request.UpdateUserRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.UserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/B.M")
@RequiredArgsConstructor
@Slf4j
public class Branch_ManagerController {

    @Autowired
    private Branch_ManagerService branch_managarservice;

    @PostMapping("/registor/BM")
    public ResponseEntity<UserResponse> registorBM(
            @Valid @RequestBody UserRegistrationRequest request) {

        log.info("Registering Branch Manager user");

        UserResponse userResponce = branch_managarservice.registorBM(request);

        return new ResponseEntity<>(userResponce, HttpStatus.CREATED);
    }
    @PutMapping("/assignrole/RE/{id}")
    public ResponseEntity<UserResponse> assignRoleRE(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {

        log.info("Assigning Representative Executive role to user id: {}", id);

        UserResponse userResponse =
                branch_managarservice.assignRoleRE(id, request);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}