package com.sgagestudio.demo.document_reminder.data.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ClientResponse(
        UUID id,
        UUID organizationId,
        String name,
        String email,
        String phone,
        String address,
        String notes,
        String status,
        UUID assignedUserId,
        List<String> tags,
        String customFields,
        Instant createdAt
) {
}
