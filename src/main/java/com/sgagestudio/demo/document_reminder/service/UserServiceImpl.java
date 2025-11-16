package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateUserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetEmployeesByOrganizationRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetEmployeesResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateUserResponse;
import com.sgagestudio.demo.document_reminder.data.entity.UserEntity;
import com.sgagestudio.demo.document_reminder.data.mapper.UserMapper;
import com.sgagestudio.demo.document_reminder.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl  implements UserService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetEmployeesResponse getAllByOrganization(GetEmployeesByOrganizationRequest request) {
        Pageable page = PageRequest.of(request.page(), request.size());

        return new GetEmployeesResponse(repository.findAllByOrganizationName(request.organizationName(), page));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserMapper().buildFromRequest(request);

        return new CreateUserResponse(repository.save(userEntity).getId());
    }
}
