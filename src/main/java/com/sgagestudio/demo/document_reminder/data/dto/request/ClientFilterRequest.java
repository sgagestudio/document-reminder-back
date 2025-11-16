package com.sgagestudio.demo.document_reminder.data.dto.request;

import java.util.List;
import java.util.UUID;

public record ClientFilterRequest(
        UUID organizationId,
        String status,
        List<String> tags,
        UUID assignedUserId
) {
}
