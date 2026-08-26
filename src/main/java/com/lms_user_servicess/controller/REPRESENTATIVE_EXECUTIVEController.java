package com.lms_user_servicess.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms_user_services.service.REPRESENTATIVE_EXECUTIVE;
import com.lms_user_servicess.dto.request.LoginRequest;
import com.lms_user_servicess.dto.request.UserRegistrationRequest;
import com.lms_user_servicess.dto.responce.LoginResponse;
import com.lms_user_servicess.dto.responce.UserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/R.E")
@RequiredArgsConstructor
@Slf4j
public class REPRESENTATIVE_EXECUTIVEController {

    @Autowired
    private REPRESENTATIVE_EXECUTIVE representative_executive;

    @PostMapping("/registor/RE")
    public ResponseEntity<UserResponse> registorRE(
            @Valid @RequestBody UserRegistrationRequest request) {

        log.info("Registering Representative Executive user");

        UserResponse userResponse = representative_executive.registorRE(request);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
	@PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
	        @Valid @RequestBody LoginRequest request) {

	    LoginResponse response = representative_executive.userlogin(request);

	    return ResponseEntity.ok(response);
	}
}