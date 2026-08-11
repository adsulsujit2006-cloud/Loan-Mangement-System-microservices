package com.lms_user_service.mapper;

import com.lms_user_service.dto.responce.AddressResponse;
import com.lms_user_service.modal.Address;

public interface AddressMapper {
	AddressResponse toResponse(Address address);

}
