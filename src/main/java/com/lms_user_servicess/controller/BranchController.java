package com.lms_user_servicess.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms_user_servicess.dto.request.CreateBranchRequest;
import com.lms_user_servicess.dto.responce.BranchResponse;
import com.lms_user_servicess.sservice.BranchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
@Slf4j
public class BranchController {

    private final BranchService branchService;

    /*
     * REST API : Register bank with required details
     */
    @PostMapping("/registor")
    public ResponseEntity<BranchResponse> createBranch(
            @Valid @RequestBody CreateBranchRequest request) {

        log.info("REST Request: Create Branch");

        BranchResponse response = branchService.createBranch(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /*
     * REST API : Get the details of a bank branch using its branch ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<BranchResponse> getBranchById(
            @PathVariable Long id) {

        log.info("REST Request : Get branch by id {}", id);

        return ResponseEntity.ok(
                branchService.getBranchById(id));
    }

    /*
     * REST API : Get all bank branch details
     */
    @GetMapping
    public ResponseEntity<List<BranchResponse>> getAllBranches() {

        log.info("REST Request : Get All Branches");

        return ResponseEntity.ok(
                branchService.getAllBranches());
    }
}