package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.TemplateRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.TemplateResponse;

import java.util.List;
import java.util.UUID;

public interface TemplateService {

    TemplateResponse create(TemplateRequest request);

    TemplateResponse update(Long id, TemplateRequest request);

    void delete(Long id);

    TemplateResponse get(Long id);

    List<TemplateResponse> list(UUID organizationId);

    List<TemplateResponse> listDefaults(UUID organizationId);

    List<TemplateResponse> listByUser(UUID organizationId, UUID userId);
}
