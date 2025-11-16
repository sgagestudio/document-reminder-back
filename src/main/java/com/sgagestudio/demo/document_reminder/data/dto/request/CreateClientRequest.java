package com.sgagestudio.demo.document_reminder.data.dto.request;

import java.util.UUID;

public record CreateClientRequest(
        String name,
        String email,
        UUID organizationId,
        String phone,
        String address
) {
}
