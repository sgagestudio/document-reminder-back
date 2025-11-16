package com.sgagestudio.demo.document_reminder.data.dto.response;

import java.time.Instant;
import java.util.UUID;

public record EmployeeCellDataResponse(
        UUID id,
        String name,
        String role,
        String email,
        String businessArea,
        Instant createdAt
) {
}
