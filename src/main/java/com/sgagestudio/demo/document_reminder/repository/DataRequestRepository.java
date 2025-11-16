package com.sgagestudio.demo.document_reminder.repository;

import com.sgagestudio.demo.document_reminder.data.DataRequestTemporality;
import com.sgagestudio.demo.document_reminder.data.entity.DataRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface DataRequestRepository extends JpaRepository<DataRequestEntity, UUID> {

    List<DataRequestEntity> findByOrganizationId(UUID organizationId);

    List<DataRequestEntity> findByTemporalityAndActiveTrueAndNextExecutionBefore(DataRequestTemporality temporality,
                                                                                 Instant nextExecution);
}
