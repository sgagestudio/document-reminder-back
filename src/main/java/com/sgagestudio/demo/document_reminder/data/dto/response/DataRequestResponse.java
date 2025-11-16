package com.sgagestudio.demo.document_reminder.data.dto.response;

import com.sgagestudio.demo.document_reminder.data.DataRequestStatus;
import com.sgagestudio.demo.document_reminder.data.DataRequestTemporality;

import java.time.Instant;
import java.util.UUID;

public record DataRequestResponse(
        UUID id,
        UUID organizationId,
        UUID clientId,
        Long templateId,
        String title,
        String description,
        DataRequestTemporality temporality,
        DataRequestStatus status,
        Instant nextExecution,
        Instant lastExecution,
        boolean active
) {
}
