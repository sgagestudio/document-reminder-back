package com.sgagestudio.demo.document_reminder.controller;

import com.sgagestudio.demo.document_reminder.data.dto.request.TemplateRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.TemplateResponse;
import com.sgagestudio.demo.document_reminder.service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/templates")
@CrossOrigin(origins = "*")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public ResponseEntity<TemplateResponse> create(@RequestBody TemplateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(templateService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemplateResponse> update(@PathVariable Long id, @RequestBody TemplateRequest request) {
        return ResponseEntity.ok(templateService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(templateService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<TemplateResponse>> list(@RequestParam UUID organizationId,
                                                       @RequestParam(required = false) UUID userId,
                                                       @RequestParam(defaultValue = "false") boolean defaults) {
        if (defaults) {
            return ResponseEntity.ok(templateService.listDefaults(organizationId));
        }
        if (userId != null) {
            return ResponseEntity.ok(templateService.listByUser(organizationId, userId));
        }
        return ResponseEntity.ok(templateService.list(organizationId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        templateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
