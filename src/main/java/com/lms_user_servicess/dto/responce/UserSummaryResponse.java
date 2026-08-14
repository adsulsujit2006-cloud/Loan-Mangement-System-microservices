package com.lms_user_servicess.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponse {

    private Long id;

    private String customerCode;

    private String fullName;

    private String email;

    private String mobileNumber;

    private Boolean active;

}

