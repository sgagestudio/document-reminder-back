package com.sgagestudio.demo.document_reminder.repository;

import com.sgagestudio.demo.document_reminder.data.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {

    List<TemplateEntity> findByOrganizationId(UUID organizationId);

    List<TemplateEntity> findByOrganizationIdAndIsDefaultTrue(UUID organizationId);

    List<TemplateEntity> findByOrganizationIdAndUserId(UUID organizationId, UUID userId);

    Optional<TemplateEntity> findByIdAndOrganizationId(Long id, UUID organizationId);
}
