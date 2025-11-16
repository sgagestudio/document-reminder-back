package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.ClientFilterRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.ClientRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    ClientResponse create(ClientRequest request);

    ClientResponse update(UUID id, ClientRequest request);

    void delete(UUID id);

    ClientResponse get(UUID id);

    Page<ClientResponse> list(UUID organizationId, Pageable pageable);

    List<ClientResponse> filter(ClientFilterRequest request);
}
