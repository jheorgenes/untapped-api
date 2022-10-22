package com.pj.untapped.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("select c from USERS c  where c.id = :id")
    User findById(@Param("id") long id);
    
    @Query("select c from USERS c  where c.email = :email")
    User findByEmail(@Param("email") String email);
    
    @Query("select c from USERS c")
    List<User> findAll();
    
    @Query("select c from USERS c")
    Page<User> findAllPage(Pageable pageable);
}
