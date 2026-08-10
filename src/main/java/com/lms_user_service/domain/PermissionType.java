package com.lms_user_service.domain;

public enum PermissionType {
	 // User Management
    CREATE_USER,
    UPDATE_USER,
    DELETE_USER,
    VIEW_USER,

    // Branch Management
    CREATE_BRANCH,
    UPDATE_BRANCH,
    DELETE_BRANCH,
    VIEW_BRANCH,

    // Role Management
    CREATE_ROLE,
    UPDATE_ROLE,
    DELETE_ROLE,
    VIEW_ROLE,

    // Permission Management
    CREATE_PERMISSION,
    UPDATE_PERMISSION,
    DELETE_PERMISSION,
    VIEW_PERMISSION,

    // Loan Type
    CREATE_LOAN_TYPE,
    UPDATE_LOAN_TYPE,
    DELETE_LOAN_TYPE,
    VIEW_LOAN_TYPE,

    // Loan Application
    APPLY_LOAN,
    APPROVE_LOAN,
    REJECT_LOAN,
    VIEW_LOAN_APPLICATION,

    // Loan
    DISBURSE_LOAN,
    VIEW_LOAN,

    // EMI
    GENERATE_EMI,
    VIEW_EMI,

    // Payment
    MAKE_PAYMENT,
    VIEW_PAYMENT,

    // Reports
    VIEW_REPORTS


}
