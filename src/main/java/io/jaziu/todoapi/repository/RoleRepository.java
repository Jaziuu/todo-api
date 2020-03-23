package io.jaziu.todoapi.repository;

import io.jaziu.todoapi.model.Role;
import io.jaziu.todoapi.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface RoleRepository extends JpaRepository<Role, Long> {
        Optional<Role> findByName(RoleName roleName);
    }
