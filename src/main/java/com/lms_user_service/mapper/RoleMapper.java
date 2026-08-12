package com.lms_user_service.mapper;

import org.mapstruct.Mapper;

import com.lms_user_service.dto.responce.AddressResponse;
import com.lms_user_service.modal.Address;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	AddressResponse toResponse(Address address);

}
