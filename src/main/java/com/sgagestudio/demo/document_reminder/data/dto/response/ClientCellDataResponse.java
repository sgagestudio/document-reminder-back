package com.sgagestudio.demo.document_reminder.data.dto.response;

import java.util.UUID;

public record ClientCellDataResponse(
        UUID id,
        String name,
        String email,
        String phone,
        String organizationName,
        int pendingRequests
) {
}
