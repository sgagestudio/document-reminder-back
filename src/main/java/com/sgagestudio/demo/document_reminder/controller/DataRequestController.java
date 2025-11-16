package com.sgagestudio.demo.document_reminder.controller;

import com.sgagestudio.demo.document_reminder.data.DataRequestTemporality;
import com.sgagestudio.demo.document_reminder.data.dto.request.DataRequestCommand;
import com.sgagestudio.demo.document_reminder.data.dto.response.DataRequestResponse;
import com.sgagestudio.demo.document_reminder.service.DataRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/data-requests")
@CrossOrigin(origins = "*")
public class DataRequestController {

    private final DataRequestService dataRequestService;

    public DataRequestController(DataRequestService dataRequestService) {
        this.dataRequestService = dataRequestService;
    }

    @PostMapping
    public ResponseEntity<DataRequestResponse> create(@RequestBody DataRequestCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dataRequestService.create(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataRequestResponse> update(@PathVariable UUID id,
                                                      @RequestBody DataRequestCommand command) {
        return ResponseEntity.ok(dataRequestService.update(id, command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataRequestResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(dataRequestService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<DataRequestResponse>> list(@RequestParam UUID organizationId) {
        return ResponseEntity.ok(dataRequestService.listByOrganization(organizationId));
    }

    @PostMapping("/{id}/pause")
    public ResponseEntity<Void> pause(@PathVariable UUID id) {
        dataRequestService.pause(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<Void> resume(@PathVariable UUID id) {
        dataRequestService.resume(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        dataRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/execute")
    public ResponseEntity<Void> execute(@RequestParam DataRequestTemporality temporality) {
        dataRequestService.processTemporality(temporality);
        return ResponseEntity.accepted().build();
    }
}
