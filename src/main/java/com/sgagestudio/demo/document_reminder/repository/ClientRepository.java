package com.sgagestudio.demo.document_reminder.repository;

import com.sgagestudio.demo.document_reminder.data.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    Page<ClientEntity> findByOrganizationId(UUID organizationId, Pageable pageable);

    @Query("SELECT c FROM ClientEntity c WHERE c.organizationId = :organizationId " +
            "AND (:status IS NULL OR c.status = :status) " +
            "AND (:assignedUserId IS NULL OR c.assignedUserId = :assignedUserId) " +
            "AND (:tags IS NULL OR EXISTS (SELECT 1 FROM c.tags tag WHERE tag IN :tags))")
    List<ClientEntity> findWithFilters(@Param("organizationId") UUID organizationId,
                                       @Param("status") String status,
                                       @Param("tags") List<String> tags,
                                       @Param("assignedUserId") UUID assignedUserId);
}
