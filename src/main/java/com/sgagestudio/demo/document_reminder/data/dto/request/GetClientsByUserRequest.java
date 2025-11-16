package com.sgagestudio.demo.document_reminder.data.dto.request;

public record GetClientsByUserRequest(
        String organizationName,
        int page,
        int size
) {
}
