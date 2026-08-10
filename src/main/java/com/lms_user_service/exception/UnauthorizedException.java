package com.lms_user_service.exception;

public class UnauthorizedException extends RuntimeException{
	UnauthorizedException(String message){
		super(message);
	}

}
