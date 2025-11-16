package com.sgagestudio.demo.document_reminder.data.dto.request;

public record GetEmployeesByOrganizationRequest(
        String organizationName,
        int page,
        int size
) {
}
