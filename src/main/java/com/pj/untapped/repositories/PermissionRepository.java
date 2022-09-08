package com.pj.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer>{

}
