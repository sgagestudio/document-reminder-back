package com.sgagestudio.demo.document_reminder.controller;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateTemplateRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetTemplatesRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateTemplateResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetTemplatesResponse;
import com.sgagestudio.demo.document_reminder.service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/template")
public class TemplateController {

    private final TemplateService service;

    public TemplateController(TemplateService service) {
        this.service = service;
    }


    @GetMapping("/")
    public ResponseEntity<GetTemplatesResponse> getTemplatesByUser(
            @RequestBody GetTemplatesRequest request
            ) {
        return ResponseEntity.ok(service.findTemplatesByUser(request));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteById(
            @RequestBody Long id
    ) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/")
    public ResponseEntity<CreateTemplateResponse> create(
            @RequestBody CreateTemplateRequest request
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

}
