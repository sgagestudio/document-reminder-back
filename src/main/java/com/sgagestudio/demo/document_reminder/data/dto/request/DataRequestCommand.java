package com.sgagestudio.demo.document_reminder.data.dto.request;

import com.sgagestudio.demo.document_reminder.data.DataRequestTemporality;

import java.util.UUID;

public record DataRequestCommand(
        UUID id,
        UUID organizationId,
        UUID clientId,
        Long templateId,
        String title,
        String description,
        DataRequestTemporality temporality
) {
}
