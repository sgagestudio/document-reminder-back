package com.sgagestudio.demo.document_reminder.data.dto.response;

import com.sgagestudio.demo.document_reminder.data.entity.UserRole;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        UUID organizationId,
        String businessArea,
        UserRole role
) {
}
