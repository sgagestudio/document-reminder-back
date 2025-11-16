package com.sgagestudio.demo.document_reminder.data.dto.request;

import java.util.List;
import java.util.UUID;

public record ClientRequest(
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
        String customFields
) {
}
