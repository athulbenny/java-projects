package com.credai.securityapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.securityapi.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    Role findByRole(String role);
}
