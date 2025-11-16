package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.UserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.UserResponse;
import com.sgagestudio.demo.document_reminder.data.entity.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserRequest request);

    UserResponse update(UUID id, UserRequest request);

    List<UserResponse> listByOrganization(UUID organizationId);

    List<UserResponse> listByOrganizationAndRole(UUID organizationId, UserRole role);

    UserResponse get(UUID id);

    void delete(UUID id);
}
