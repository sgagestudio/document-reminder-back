package com.sgagestudio.demo.document_reminder.data.mapper;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateTemplateRequest;
import com.sgagestudio.demo.document_reminder.data.entity.TemplateEntity;

public class TemplateMapper {

    public TemplateEntity buildFromRequest(CreateTemplateRequest request) {
        TemplateEntity entity = new TemplateEntity();

        entity.setType(request.type());
        entity.setName(request.name());
        entity.setSubject(request.subject());
        entity.setBody(request.body());
        entity.setUserId(request.userId());

        return entity;
    }

}
