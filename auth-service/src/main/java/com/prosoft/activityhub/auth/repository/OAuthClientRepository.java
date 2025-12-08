package com.prosoft.activityhub.auth.repository;

import java.util.Optional;
import com.prosoft.activityhub.auth.entity.OAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthClientRepository extends JpaRepository<OAuthClient, Long> {
    Optional<OAuthClient> findByClientId(String clientId);
}
