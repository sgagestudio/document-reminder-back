package com.sgagestudio.demo.document_reminder.data.dto.response;

import java.util.UUID;

public record OrganizationResponse(
        UUID id,
        String name,
        String email,
        String domain,
        String fromDisplayName,
        String timezone
) {
}
