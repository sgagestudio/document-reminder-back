package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateTemplateRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetTemplatesRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateTemplateResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetTemplatesResponse;

public interface TemplateService {
    GetTemplatesResponse findTemplatesByUser(GetTemplatesRequest request);

    void deleteById(Long id);

    CreateTemplateResponse create(CreateTemplateRequest request);
}
