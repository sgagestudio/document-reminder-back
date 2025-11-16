package com.sgagestudio.demo.document_reminder.data.mapper;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateClientRequest;
import com.sgagestudio.demo.document_reminder.data.entity.ClientEntity;

import java.time.Instant;
import java.util.UUID;

public class ClientMapper {

    public ClientEntity buildEntityFromRequest(CreateClientRequest request) {
        ClientEntity entity = new ClientEntity();
        entity.setId(UUID.randomUUID());
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setOrganizationId(request.organizationId());
        entity.setPhone(request.phone());
        entity.setCreatedAt(Instant.now());
        entity.setAddress(request.address());

        return entity;
    }
}
