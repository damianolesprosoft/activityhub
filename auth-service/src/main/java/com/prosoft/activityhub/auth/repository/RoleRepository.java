package com.prosoft.activityhub.auth.repository;

import java.util.Optional;
import com.prosoft.activityhub.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
