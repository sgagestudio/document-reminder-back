package com.sgagestudio.demo.document_reminder.repository;

import com.sgagestudio.demo.document_reminder.data.entity.UserEntity;
import com.sgagestudio.demo.document_reminder.data.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    List<UserEntity> findByOrganizationId(UUID organizationId);

    List<UserEntity> findByOrganizationIdAndRole(UUID organizationId, UserRole role);

    Optional<UserEntity> findByEmail(String email);
}
