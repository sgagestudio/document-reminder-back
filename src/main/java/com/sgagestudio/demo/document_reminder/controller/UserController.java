package com.sgagestudio.demo.document_reminder.controller;

import com.sgagestudio.demo.document_reminder.data.dto.request.CreateUserRequest;
import com.sgagestudio.demo.document_reminder.data.dto.request.GetEmployeesByOrganizationRequest;
import com.sgagestudio.demo.document_reminder.data.dto.response.GetEmployeesResponse;
import com.sgagestudio.demo.document_reminder.data.dto.response.CreateUserResponse;
import com.sgagestudio.demo.document_reminder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<GetEmployeesResponse> getEmployeesByOrganization(
            @RequestBody GetEmployeesByOrganizationRequest request
            ) {
        return ResponseEntity.ok(service.getAllByOrganization(request));
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteById(
            @RequestBody UUID id
            ) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/")
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody CreateUserRequest request
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(request));
    }
}
