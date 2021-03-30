package com.bill.employee.routing.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bill.employee.routing.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
