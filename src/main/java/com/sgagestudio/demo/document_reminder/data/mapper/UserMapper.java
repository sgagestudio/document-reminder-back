package com.sgagestudio.demo.document_reminder.data.mapper;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateUserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.RegisterRequest;
import com.sgagestudio.demo.document_reminder.data.entity.UserEntity;

import java.util.UUID;

public class UserMapper {

    public UserEntity buildFromRequest(CreateUserRequest request) {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPassword(request.password());
        entity.setOrganizationId(request.organizationId());
        entity.setBusinessArea(request.businessArea());
        entity.setRole(request.role());

        return entity;
    }

    public UserEntity buildFromRequest(RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setOrganizationId(request.organizationId());
        user.setBusinessArea(request.businessArea());
        user.setRole(request.role());

        return user;
    }

}
