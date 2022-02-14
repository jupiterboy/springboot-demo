package com.jupiterboy.springboot.jpa.sharding.repository;

import com.jupiterboy.springboot.jpa.sharding.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}