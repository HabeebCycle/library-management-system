package com.habeebcycle.lms.service.userservice.repository;

import com.habeebcycle.lms.service.userservice.model.Role;
import com.habeebcycle.lms.service.userservice.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
