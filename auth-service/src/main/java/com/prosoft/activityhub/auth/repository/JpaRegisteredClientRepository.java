package com.prosoft.activityhub.auth.repository;

import com.prosoft.activityhub.auth.entity.OAuthClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class JpaRegisteredClientRepository implements RegisteredClientRepository {

    private final OAuthClientRepository repo;
    private final PasswordEncoder encoder;

    public JpaRegisteredClientRepository(OAuthClientRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("Clients are managed through Flyway and SQL, not programmatically");
    }

    @Override
    public RegisteredClient findById(String id) {
        return repo.findById(Long.valueOf(id))
            .map(this::mapToRegisteredClient)
            .orElse(null);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return repo.findByClientId(clientId)
            .map(this::mapToRegisteredClient)
            .orElse(null);
    }

    private RegisteredClient mapToRegisteredClient(OAuthClient c) {
        RegisteredClient.Builder builder = RegisteredClient.withId(c.getId().toString())
            .clientId(c.getClientId())
            .clientSecret(encoder.encode(c.getClientSecret()))
            .redirectUri(c.getRedirectUri());

        for (String g : c.getAuthorizedGrantTypes().split(",")) {
            builder.authorizationGrantType(new AuthorizationGrantType(g.trim()));
        }

        for (String s : c.getScopes().split(",")) {
            builder.scope(s.trim());
        }

        return builder.build();
    }
}
