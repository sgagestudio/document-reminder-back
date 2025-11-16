package com.sgagestudio.demo.document_reminder.data.dto.response;

public record TemplateCellDataResponse(
        Long id,
        String name,
        String subject,
        String body,
        String userId
) {
}
