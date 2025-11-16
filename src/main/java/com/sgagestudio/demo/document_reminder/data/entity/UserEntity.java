package com.sgagestudio.demo.document_reminder.data.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    // 🔥 Relación directa con organización
    @Column(name = "organization_id", nullable = false)
    private UUID organizationId;

    @Column(name = "business_area")
    private String businessArea;

    // 🔥 Enumeración de roles
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public static class Builder {

        private final UserEntity instance;

        public Builder() {
            this.instance = new UserEntity();
        }

        public Builder setName(String name) {
            instance.setName(name);
            return this;
        }

        public Builder setEmail(String email) {
            instance.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            instance.setPassword(password);
            return this;
        }

        public Builder setOrganizationId(UUID organization) {
            instance.setOrganizationId(organization);
            return this;
        }

        public Builder setBusinessArea(String businessArea) {
            instance.setBusinessArea(businessArea);
            return this;
        }

        public Builder setRole(UserRole role) {
            instance.setRole(role);
            return this;
        }

        public UserEntity build() {
            return instance;
        }
    }
}
