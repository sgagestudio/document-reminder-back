package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateClientRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetClientsByUserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateClientResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetClientsByUserResponse;

import java.util.UUID;

public interface ClientService {

    GetClientsByUserResponse findAllByUser(GetClientsByUserRequest request);

    void deleteById(UUID id);

    CreateClientResponse createClient(CreateClientRequest request);
}
