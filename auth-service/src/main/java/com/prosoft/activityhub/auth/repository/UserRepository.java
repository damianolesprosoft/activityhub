package com.prosoft.activityhub.auth.repository;

import java.util.Optional;
import com.prosoft.activityhub.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
