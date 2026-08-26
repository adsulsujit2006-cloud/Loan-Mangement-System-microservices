package com.lms_user_servicess.mapper;

import org.mapstruct.Mapper;

import com.lms_user_servicess.dto.responce.AddressResponse;
import com.lms_user_servicess.modal.Address;



@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressResponse toResponse(Address address);
}