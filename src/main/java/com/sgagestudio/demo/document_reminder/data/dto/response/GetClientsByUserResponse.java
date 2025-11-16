package com.sgagestudio.demo.document_reminder.data.dto.response;

import org.springframework.data.domain.Slice;

public record GetClientsByUserResponse(
        Slice<ClientCellDataResponse> clients
) {
}
