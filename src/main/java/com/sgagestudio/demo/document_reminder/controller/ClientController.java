package com.sgagestudio.demo.document_reminder.controller;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateClientRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetClientsByUserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateClientResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetClientsByUserResponse;
import com.sgagestudio.demo.document_reminder.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<GetClientsByUserResponse> getClientsByUser(
            @RequestBody GetClientsByUserRequest request
            ) {
        return ResponseEntity.ok(service.findAllByUser(request));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteById(
            @RequestBody UUID id
            ) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/")
    public ResponseEntity<CreateClientResponse> createClient(
            @RequestBody CreateClientRequest request
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createClient(request));
    }

}
