package com.sgagestudio.demo.document_reminder.data.dto.request;

public record OrganizationRequest(
        String name,
        String email,
        String domain,
        String fromDisplayName,
        String timezone
) {
}
