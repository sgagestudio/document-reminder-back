package com.sgagestudio.demo.document_reminder.data.dto.request;

import java.util.UUID;

public record GetTemplatesRequest(
        UUID userId,
        String type
) {
}
