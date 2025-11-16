package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.DataRequestTemporality;
import com.sgagestudio.demo.document_reminder.data.dto.request.DataRequestCommand;
import com.sgagestudio.demo.document_reminder.data.dto.response.DataRequestResponse;

import java.util.List;
import java.util.UUID;

public interface DataRequestService {

    DataRequestResponse create(DataRequestCommand command);

    DataRequestResponse update(UUID id, DataRequestCommand command);

    DataRequestResponse get(UUID id);

    void delete(UUID id);

    List<DataRequestResponse> listByOrganization(UUID organizationId);

    void pause(UUID id);

    void resume(UUID id);

    void processTemporality(DataRequestTemporality temporality);
}
