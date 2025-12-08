package com.prosoft.activityhub.auth.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "oauth_clients")
public class OAuthClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "redirect_uri")
    private String redirectUri;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
        createdBy = "system";
        modifiedBy = "system";
    }

    @PreUpdate
    public void preUpdate() {
        modifiedAt = LocalDateTime.now();
        modifiedBy = "system";
    }

    // Gettery / Settery
}
