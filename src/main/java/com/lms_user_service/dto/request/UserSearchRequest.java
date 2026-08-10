package com.lms_user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest {

    private String customerCode;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private Boolean active;

    private Long roleId;

    private Long branchId;

    private Integer page = 0;

    private Integer size = 10;

    private String sortBy = "createdAt";

    private String sortDirection = "DESC";

}

