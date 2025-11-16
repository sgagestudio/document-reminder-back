package com.sgagestudio.demo.document_reminder.data.dto.response;

import java.util.List;

public record GetTemplatesResponse(List<TemplateCellDataResponse> templates) {
}
