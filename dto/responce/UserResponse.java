package com.lms_user_servicess.dto.responce;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String customerCode;

    private String firstName;

    private String middleName;

    private String lastName;

    private String fullName;

    private String email;

    private String mobileNumber;

    private LocalDate dateOfBirth;

    private String gender;

    private String aadhaarNumber;

    private String panNumber;

    private Boolean active;

    private String profilePhoto;

    private BranchResponse branch;

    private AddressResponse address;

    private Set<RoleResponse> roles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
