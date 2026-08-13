package com.lms_user_servicess.util;
import org.springframework.stereotype.Component;

@Component
public class CustomerCodeGenerator {

    public String generateCustomerCode(Long userId) {

        return String.format("CUS%06d", userId);

    }

}