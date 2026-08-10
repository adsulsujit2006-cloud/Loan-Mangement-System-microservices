package com.lms_user_service.util;

import org.springframework.stereotype.Component;

@Component
public class CustomerCodeGenerator {
	 public String generateCustomerCode(Long userId) {

	        return String.format("CUS%06d", userId);

	 }

}
