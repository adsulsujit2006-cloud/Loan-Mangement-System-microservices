package com.lms_user_service.exception;

public class ForbiddenException extends RuntimeException {
	ForbiddenException(String message){
		super(message);
	}

}
