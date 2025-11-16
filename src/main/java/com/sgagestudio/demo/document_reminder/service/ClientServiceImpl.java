package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.ClientFilterRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.ClientRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.ClientResponse;
import com.sgagestudio.demo.document_reminder.data.entity.ClientEntity;
import com.sgagestudio.demo.document_reminder.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientResponse create(ClientRequest request) {
        ClientEntity entity = map(new ClientEntity(), request);
        entity.setCreatedAt(Instant.now());
        return map(repository.save(entity));
    }

    @Override
    public ClientResponse update(UUID id, ClientRequest request) {
        ClientEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        return map(repository.save(map(entity, request)));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public ClientResponse get(UUID id) {
        return repository.findById(id).map(this::map)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    @Override
    public Page<ClientResponse> list(UUID organizationId, Pageable pageable) {
        return repository.findByOrganizationId(organizationId, pageable).map(this::map);
    }

    @Override
    public List<ClientResponse> filter(ClientFilterRequest request) {
        List<String> tags = (request.tags() == null || request.tags().isEmpty()) ? null : request.tags();
        return repository.findWithFilters(request.organizationId(), request.status(), tags, request.assignedUserId())
                .stream().map(this::map).toList();
    }

    private ClientEntity map(ClientEntity entity, ClientRequest request) {
        entity.setOrganizationId(request.organizationId());
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPhone(request.phone());
        entity.setAddress(request.address());
        entity.setNotes(request.notes());
        entity.setStatus(request.status());
        entity.setAssignedUserId(request.assignedUserId());
        entity.setTags(request.tags());
        entity.setCustomFields(request.customFields());
        return entity;
    }

    private ClientResponse map(ClientEntity entity) {
        return new ClientResponse(
                entity.getId(),
                entity.getOrganizationId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getNotes(),
                entity.getStatus(),
                entity.getAssignedUserId(),
                entity.getTags(),
                entity.getCustomFields(),
                entity.getCreatedAt()
        );
    }
}
