package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.OrganizationRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.OrganizationResponse;
import com.sgagestudio.demo.document_reminder.data.entity.OrganizationEntity;
import com.sgagestudio.demo.document_reminder.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrganizationResponse create(OrganizationRequest request) {
        OrganizationEntity entity = map(request);
        return map(repository.save(entity));
    }

    @Override
    public OrganizationResponse update(UUID id, OrganizationRequest request) {
        OrganizationEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Organization not found"));
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setDomain(request.domain());
        entity.setFromDisplayName(request.fromDisplayName());
        entity.setTimezone(request.timezone());
        return map(repository.save(entity));
    }

    @Override
    public OrganizationResponse get(UUID id) {
        return repository.findById(id)
                .map(this::map)
                .orElseThrow(() -> new IllegalArgumentException("Organization not found"));
    }

    @Override
    public List<OrganizationResponse> list() {
        return repository.findAll().stream().map(this::map).toList();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private OrganizationEntity map(OrganizationRequest request) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setDomain(request.domain());
        entity.setFromDisplayName(request.fromDisplayName());
        entity.setTimezone(request.timezone() == null ? "UTC" : request.timezone());
        return entity;
    }

    private OrganizationResponse map(OrganizationEntity entity) {
        return new OrganizationResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getDomain(),
                entity.getFromDisplayName(),
                entity.getTimezone()
        );
    }
}
