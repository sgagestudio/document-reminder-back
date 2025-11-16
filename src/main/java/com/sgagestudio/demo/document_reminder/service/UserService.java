package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateUserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetEmployeesByOrganizationRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetEmployeesResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateUserResponse;

import java.util.UUID;

public interface UserService {
    GetEmployeesResponse getAllByOrganization(GetEmployeesByOrganizationRequest request);

    void deleteById(UUID id);

    CreateUserResponse createUser(CreateUserRequest request);
}
