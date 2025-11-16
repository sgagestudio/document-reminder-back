package com.sgagestudio.demo.document_reminder.service;

import com.sgagestudio.demo.document_reminder.data.DataRequestStatus;
import com.sgagestudio.demo.document_reminder.data.DataRequestTemporality;
import com.sgagestudio.demo.document_reminder.data.dto.request.DataRequestCommand;
import com.sgagestudio.demo.document_reminder.data.dto.response.DataRequestResponse;
import com.sgagestudio.demo.document_reminder.data.entity.ClientEntity;
import com.sgagestudio.demo.document_reminder.data.entity.DataRequestEntity;
import com.sgagestudio.demo.document_reminder.data.entity.OrganizationEntity;
import com.sgagestudio.demo.document_reminder.data.entity.TemplateEntity;
import com.sgagestudio.demo.document_reminder.repository.ClientRepository;
import com.sgagestudio.demo.document_reminder.repository.DataRequestRepository;
import com.sgagestudio.demo.document_reminder.repository.OrganizationRepository;
import com.sgagestudio.demo.document_reminder.repository.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class DataRequestServiceImpl implements DataRequestService {

    private final DataRequestRepository repository;
    private final ClientRepository clientRepository;
    private final TemplateRepository templateRepository;
    private final OrganizationRepository organizationRepository;
    private final GmailSenderService gmailSenderService;

    public DataRequestServiceImpl(DataRequestRepository repository,
                                  ClientRepository clientRepository,
                                  TemplateRepository templateRepository,
                                  OrganizationRepository organizationRepository,
                                  GmailSenderService gmailSenderService) {
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.templateRepository = templateRepository;
        this.organizationRepository = organizationRepository;
        this.gmailSenderService = gmailSenderService;
    }

    @Override
    public DataRequestResponse create(DataRequestCommand command) {
        DataRequestEntity entity = map(new DataRequestEntity(), command);
        entity.setNextExecution(Instant.now());
        return map(repository.save(entity));
    }

    @Override
    public DataRequestResponse update(UUID id, DataRequestCommand command) {
        DataRequestEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data request not found"));
        return map(repository.save(map(entity, command)));
    }

    @Override
    public DataRequestResponse get(UUID id) {
        return repository.findById(id).map(this::map)
                .orElseThrow(() -> new IllegalArgumentException("Data request not found"));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<DataRequestResponse> listByOrganization(UUID organizationId) {
        return repository.findByOrganizationId(organizationId).stream().map(this::map).toList();
    }

    @Override
    public void pause(UUID id) {
        updateStatus(id, DataRequestStatus.PAUSED);
    }

    @Override
    public void resume(UUID id) {
        updateStatus(id, DataRequestStatus.PENDING);
    }

    @Override
    public void processTemporality(DataRequestTemporality temporality) {
        Instant now = Instant.now();
        List<DataRequestEntity> due = repository
                .findByTemporalityAndActiveTrueAndNextExecutionBefore(temporality, now);
        due.forEach(entity -> execute(entity, now));
    }

    private void execute(DataRequestEntity entity, Instant executionInstant) {
        ClientEntity client = clientRepository.findById(entity.getClientId())
                .orElseThrow(() -> new IllegalStateException("Client not found for data request"));
        TemplateEntity template = templateRepository.findById(entity.getTemplateId())
                .orElseThrow(() -> new IllegalStateException("Template not found for data request"));
        OrganizationEntity organization = organizationRepository.findById(entity.getOrganizationId())
                .orElseThrow(() -> new IllegalStateException("Organization not found for data request"));

        String subject = template.getSubject();
        String body = template.getBody()
                .replace("{{clientName}}", client.getName() == null ? "" : client.getName())
                .replace("{{organization}}", organization.getName());

        gmailSenderService.sendMail(
                organization.computeFromAddress(),
                organization.getFromDisplayName(),
                client.getEmail(),
                subject,
                body,
                template.getLanguage()
        );

        entity.setLastExecution(executionInstant);
        entity.setNextExecution(calculateNextExecution(entity.getTemporality(), executionInstant));
        entity.setStatus(DataRequestStatus.RUNNING);
        repository.save(entity);
    }

    private Instant calculateNextExecution(DataRequestTemporality temporality, Instant lastExecution) {
        return switch (temporality) {
            case DAILY -> lastExecution.plus(1, ChronoUnit.DAYS);
            case WEEKLY -> lastExecution.plus(7, ChronoUnit.DAYS);
            case MONTHLY -> lastExecution.plus(30, ChronoUnit.DAYS);
        };
    }

    private void updateStatus(UUID id, DataRequestStatus status) {
        DataRequestEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data request not found"));
        entity.setStatus(status);
        entity.setActive(!DataRequestStatus.PAUSED.equals(status));
        repository.save(entity);
    }

    private DataRequestEntity map(DataRequestEntity entity, DataRequestCommand command) {
        entity.setOrganizationId(command.organizationId());
        entity.setClientId(command.clientId());
        entity.setTemplateId(command.templateId());
        entity.setTitle(command.title());
        entity.setDescription(command.description());
        entity.setTemporality(command.temporality());
        return entity;
    }

    private DataRequestResponse map(DataRequestEntity entity) {
        return new DataRequestResponse(
                entity.getId(),
                entity.getOrganizationId(),
                entity.getClientId(),
                entity.getTemplateId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getTemporality(),
                entity.getStatus(),
                entity.getNextExecution(),
                entity.getLastExecution(),
                entity.isActive()
        );
    }
}
