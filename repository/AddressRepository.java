package com.lms_user_servicess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms_user_servicess.modal.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
