package com.pj.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.TicketsOrder;

@Repository
public interface TicketsOrderRepository extends JpaRepository<TicketsOrder, Integer>{

}
