package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.TemplateRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.TemplateResponse;
import com.sgagestudio.demo.document_reminder.data.entity.TemplateEntity;
import com.sgagestudio.demo.document_reminder.repository.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository repository;

    public TemplateServiceImpl(TemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public TemplateResponse create(TemplateRequest request) {
        TemplateEntity entity = map(new TemplateEntity(), request);
        return map(repository.save(entity));
    }

    @Override
    public TemplateResponse update(Long id, TemplateRequest request) {
        TemplateEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template not found"));
        return map(repository.save(map(entity, request)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TemplateResponse get(Long id) {
        return repository.findById(id).map(this::map)
                .orElseThrow(() -> new IllegalArgumentException("Template not found"));
    }

    @Override
    public List<TemplateResponse> list(UUID organizationId) {
        return repository.findByOrganizationId(organizationId).stream().map(this::map).toList();
    }

    @Override
    public List<TemplateResponse> listDefaults(UUID organizationId) {
        return repository.findByOrganizationIdAndIsDefaultTrue(organizationId).stream().map(this::map).toList();
    }

    @Override
    public List<TemplateResponse> listByUser(UUID organizationId, UUID userId) {
        return repository.findByOrganizationIdAndUserId(organizationId, userId).stream().map(this::map).toList();
    }

    private TemplateEntity map(TemplateEntity entity, TemplateRequest request) {
        entity.setOrganizationId(request.organizationId());
        entity.setType(request.type());
        entity.setName(request.name());
        entity.setSubject(request.subject());
        entity.setBody(request.body());
        entity.setLanguage(request.language());
        entity.setUserId(request.userId());
        entity.setDefault(request.isDefault());
        return entity;
    }

    private TemplateResponse map(TemplateEntity entity) {
        return new TemplateResponse(
                entity.getId(),
                entity.getOrganizationId(),
                entity.getType(),
                entity.getName(),
                entity.getSubject(),
                entity.getBody(),
                entity.getLanguage(),
                entity.getUserId(),
                entity.isDefault()
        );
    }
}
