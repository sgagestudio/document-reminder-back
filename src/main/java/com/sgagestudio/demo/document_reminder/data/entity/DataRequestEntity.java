package com.sgagestudio.demo.document_reminder.data.entity;

import com.sgagestudio.demo.document_reminder.data.DataRequestStatus;
import com.sgagestudio.demo.document_reminder.data.DataRequestTemporality;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "data_requests")
public class DataRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "organization_id", nullable = false)
    private UUID organizationId;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "template_id", nullable = false)
    private Long templateId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "temporality", nullable = false)
    private DataRequestTemporality temporality;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DataRequestStatus status = DataRequestStatus.PENDING;

    @Column(name = "next_execution")
    private Instant nextExecution;

    @Column(name = "last_execution")
    private Instant lastExecution;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataRequestTemporality getTemporality() {
        return temporality;
    }

    public void setTemporality(DataRequestTemporality temporality) {
        this.temporality = temporality;
    }

    public DataRequestStatus getStatus() {
        return status;
    }

    public void setStatus(DataRequestStatus status) {
        this.status = status;
    }

    public Instant getNextExecution() {
        return nextExecution;
    }

    public void setNextExecution(Instant nextExecution) {
        this.nextExecution = nextExecution;
    }

    public Instant getLastExecution() {
        return lastExecution;
    }

    public void setLastExecution(Instant lastExecution) {
        this.lastExecution = lastExecution;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
