package com.pj.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
