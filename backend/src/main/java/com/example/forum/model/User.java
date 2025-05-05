package com.example.forum.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Column(name = "avatar_url", nullable = true)
    private String avatarUrl;

    @Column(name = "bio", columnDefinition = "TEXT", nullable = true)
    private String bio;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "display_name", nullable = true, length = 50)
    private String displayName;

    @Column(name = "email", nullable = true)
    private String email;

    @Builder.Default
    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified = false;

    @Column(name = "firebase_uid", nullable = false, unique = true)
    private String firebaseUid;

    @ToString.Include
    @EqualsAndHashCode.Include
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "last_login_at", nullable = true)
    private Instant lastLoginAt;

    @Column(name = "location", nullable = true)
    private String location;

    @Builder.Default
    @Column(name = "reputation_points", nullable = false)
    private Integer reputationPoints = 0;

    @Builder.Default
    @Column(name = "role", nullable = false)
    private String role = "USER";

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = true)
    private UserRole userRole;

    @Column(name = "website", nullable = true)
    private String website;
}
