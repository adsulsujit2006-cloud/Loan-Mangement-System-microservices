package com.lms_user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms_user_service.modal.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
