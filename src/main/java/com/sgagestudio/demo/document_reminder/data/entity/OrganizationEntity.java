package com.sgagestudio.demo.document_reminder.data.entity;

import jakarta.persistence.*;

import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "organizations")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "from_display_name", nullable = false)
    private String fromDisplayName;

    @Column(name = "timezone", nullable = false)
    private String timezone = "UTC";

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFromDisplayName() {
        return fromDisplayName;
    }

    public void setFromDisplayName(String fromDisplayName) {
        this.fromDisplayName = fromDisplayName;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String computeFromAddress() {
        String slug = name == null ? "team" : name
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]", "");
        if (slug.isBlank()) {
            slug = "team";
        }
        return slug + "@sgagestudio.com";
    }
}
