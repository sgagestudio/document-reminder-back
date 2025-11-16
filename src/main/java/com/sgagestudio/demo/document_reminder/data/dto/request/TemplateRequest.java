package com.sgagestudio.demo.document_reminder.data.dto.request;

import java.util.UUID;

public record TemplateRequest(
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
