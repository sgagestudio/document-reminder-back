package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateClientRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetClientsByUserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateClientResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetClientsByUserResponse;
import com.sgagestudio.demo.document_reminder.data.entity.ClientEntity;
import com.sgagestudio.demo.document_reminder.data.mapper.ClientMapper;
import com.sgagestudio.demo.document_reminder.repository.ClientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }


    @Override
    public GetClientsByUserResponse findAllByUser(GetClientsByUserRequest request) {
        Pageable page = PageRequest.of(request.page(), request.size());

        return new GetClientsByUserResponse(repository.findAllByOrganizationName(request.organizationName(), page));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public CreateClientResponse createClient(CreateClientRequest request) {
        ClientEntity entity = new ClientMapper().buildEntityFromRequest(request);

        return new CreateClientResponse(repository.save(entity).getId());
    }
}
