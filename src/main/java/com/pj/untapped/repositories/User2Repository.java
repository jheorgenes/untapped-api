package com.pj.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.User2;

@Repository
public interface User2Repository extends JpaRepository<User2, Integer>{

}
