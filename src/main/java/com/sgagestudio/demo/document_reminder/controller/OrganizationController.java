package com.sgagestudio.demo.document_reminder.controller;

import com.sgagestudio.demo.document_reminder.data.dto.request.OrganizationRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.OrganizationResponse;
import com.sgagestudio.demo.document_reminder.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations")
@CrossOrigin(origins = "*")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<OrganizationResponse> create(@RequestBody OrganizationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponse> update(@PathVariable UUID id,
                                                       @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(organizationService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponse>> list() {
        return ResponseEntity.ok(organizationService.list());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
