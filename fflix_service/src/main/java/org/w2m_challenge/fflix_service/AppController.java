package org.w2m_challenge.fflix_service;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final String applicationName;

    public AppController(@Value("${spring.application.name}") String applicationName) {
        this.applicationName = applicationName;
    }

    @Operation(summary = "Get application name")
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> hello() {
        return ResponseEntity.ok(applicationName);
    }
}
