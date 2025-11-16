package com.sgagestudio.demo.document_reminder.data.dto.request;

import java.util.UUID;

public record CreateTemplateRequest(
        String type,
        String name,
        String subject,
        String body,
        UUID userId
) {
}
