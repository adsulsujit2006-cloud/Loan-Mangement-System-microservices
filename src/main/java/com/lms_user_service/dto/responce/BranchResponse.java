package com.lms_user_service.dto.responce;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchResponse {

    private Long id;

    private String branchCode;

    private String branchName;

    private String ifscCode;

    private String email;

    private String phone;

    private String managerName;

    private String address;

    private String city;

    private String state;

    private String country;

    private String pinCode;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
