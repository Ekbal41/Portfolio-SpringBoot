package com.asifekbal.portfolio.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.asifekbal.portfolio.model.Role;



public interface RolesRepository extends JpaRepository<Role,Integer> {

    Role findByName(String string);
    
}