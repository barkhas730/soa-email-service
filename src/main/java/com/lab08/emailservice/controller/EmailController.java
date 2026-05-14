package com.lab08.emailservice.controller;

import com.lab08.emailservice.dto.EmailFailRequest;
import com.lab08.emailservice.dto.EmailSuccessRequest;
import com.lab08.emailservice.service.EmailNotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailNotificationService emailNotificationService;

    public EmailController(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping("/success")
    public ResponseEntity<Map<String, String>> sendSuccessEmail(@Valid @RequestBody EmailSuccessRequest request) {
        try {
            emailNotificationService.sendSuccessEmail(
                    request.getTo(),
                    request.getUserName(),
                    request.getFileName(),
                    request.getFileUrl()
            );
            return ResponseEntity.ok(Map.of("message", "Success email sent."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("message", "Email send failed: " + ex.getMessage()));
        }
    }

    @PostMapping("/fail")
    public ResponseEntity<Map<String, String>> sendFailEmail(@Valid @RequestBody EmailFailRequest request) {
        try {
            emailNotificationService.sendFailEmail(
                    request.getTo(),
                    request.getUserName(),
                    request.getFileName(),
                    request.getReason()
            );
            return ResponseEntity.ok(Map.of("message", "Fail email sent."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("message", "Email send failed: " + ex.getMessage()));
        }
    }
}
