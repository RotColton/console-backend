package com.rot.console.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CVController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/download-cv")
    public Mono<ResponseEntity<Resource>> downloadCV() {
        return Mono.fromSupplier(() -> {
            Resource resource = new ClassPathResource("files/resume.pdf");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"resume.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        });
    }
}
