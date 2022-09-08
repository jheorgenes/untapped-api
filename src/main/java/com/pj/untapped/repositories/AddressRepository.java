package com.pj.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
