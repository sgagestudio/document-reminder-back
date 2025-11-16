package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.dto.request.UserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.UserResponse;
import com.sgagestudio.demo.document_reminder.data.entity.UserEntity;
import com.sgagestudio.demo.document_reminder.data.entity.UserRole;
import com.sgagestudio.demo.document_reminder.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse create(UserRequest request) {
        UserEntity entity = map(new UserEntity(), request);
        entity.setPassword(passwordEncoder.encode(request.password()));
        return map(repository.save(entity));
    }

    @Override
    public UserResponse update(UUID id, UserRequest request) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        map(entity, request);
        if (request.password() != null) {
            entity.setPassword(passwordEncoder.encode(request.password()));
        }
        return map(repository.save(entity));
    }

    @Override
    public List<UserResponse> listByOrganization(UUID organizationId) {
        return repository.findByOrganizationId(organizationId).stream().map(this::map).toList();
    }

    @Override
    public List<UserResponse> listByOrganizationAndRole(UUID organizationId, UserRole role) {
        return repository.findByOrganizationIdAndRole(organizationId, role).stream().map(this::map).toList();
    }

    @Override
    public UserResponse get(UUID id) {
        return repository.findById(id).map(this::map)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private UserEntity map(UserEntity entity, UserRequest request) {
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setOrganizationId(request.organizationId());
        entity.setBusinessArea(request.businessArea());
        entity.setRole(request.role() == null ? UserRole.WORKER : request.role());
        return entity;
    }

    private UserResponse map(UserEntity entity) {
        return new UserResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getOrganizationId(),
                entity.getBusinessArea(),
                entity.getRole()
        );
    }
}
