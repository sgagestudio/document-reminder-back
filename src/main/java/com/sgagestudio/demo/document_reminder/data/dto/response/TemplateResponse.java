package com.sgagestudio.demo.document_reminder.data.dto.response;

import java.util.UUID;

public record TemplateResponse(
        Long id,
        UUID organizationId,
        String type,
        String name,
        String subject,
        String body,
        String language,
        UUID userId,
        boolean isDefault
) {
}
