package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateTemplateRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetTemplatesRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateTemplateResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetTemplatesResponse;
import com.sgagestudio.demo.document_reminder.data.mapper.TemplateMapper;
import com.sgagestudio.demo.document_reminder.repository.TemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService{

    private final TemplateRepository repository;

    public TemplateServiceImpl(TemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetTemplatesResponse findTemplatesByUser(GetTemplatesRequest request) {
        return new GetTemplatesResponse(repository.findAllByUserIdAndType(request.userId(), request.type()));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CreateTemplateResponse create(CreateTemplateRequest request) {
        return new CreateTemplateResponse(repository.save(new TemplateMapper().buildFromRequest(request)).getId());
    }
}
