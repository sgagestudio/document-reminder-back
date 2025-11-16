package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.OrganizationRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.OrganizationResponse;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {

    OrganizationResponse create(OrganizationRequest request);

    OrganizationResponse update(UUID id, OrganizationRequest request);

    OrganizationResponse get(UUID id);

    List<OrganizationResponse> list();

    void delete(UUID id);
}
