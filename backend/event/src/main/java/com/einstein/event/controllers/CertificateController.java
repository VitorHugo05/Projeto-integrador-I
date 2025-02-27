package com.einstein.event.controllers;

import com.einstein.event.feign.CertificateServiceClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certificate")
public class CertificateController {
    private final CertificateServiceClient certificateServiceClient;

    public CertificateController(CertificateServiceClient certificateServiceClient) {
        this.certificateServiceClient = certificateServiceClient;
    }
}
