package com.lms_user_service.util;

public class AppConstants {

    private AppConstants() {
    }

    // Roles
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String ROLE_ADMIN = "ADMIN";

    // Status
    public static final Boolean ACTIVE = true;
    public static final Boolean INACTIVE = false;

    // Success Messages
    public static final String USER_REGISTERED = "User registered successfully.";
    public static final String USER_UPDATED = "User updated successfully.";
    public static final String USER_DELETED = "User deleted successfully.";
    public static final String PASSWORD_CHANGED = "Password changed successfully.";

    // Error Messages
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String ROLE_NOT_FOUND = "Role not found.";
    public static final String BRANCH_NOT_FOUND = "Branch not found.";

}
