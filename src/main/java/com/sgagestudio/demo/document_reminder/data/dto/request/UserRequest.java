package com.sgagestudio.demo.document_reminder.data.dto.request;

import com.sgagestudio.demo.document_reminder.data.entity.UserRole;

import java.util.UUID;

public record UserRequest(
        UUID id,
        String name,
        String email,
        String password,
        UUID organizationId,
        String businessArea,
        UserRole role
) {
}
