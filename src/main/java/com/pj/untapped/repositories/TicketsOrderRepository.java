package com.pj.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.TicketsOrder;

@Repository
public interface TicketsOrderRepository extends JpaRepository<TicketsOrder, Integer>{

    @Query("SELECT t FROM TicketsOrder t WHERE t.qrCode = :qrcode")
    TicketsOrder findByQrCode(@Param("qrcode") String qrcode);
}
