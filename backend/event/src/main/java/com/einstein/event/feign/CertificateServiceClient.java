package com.einstein.event.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "python-service", url = "http://localhost:3333")
public interface CertificateServiceClient {
    @PostMapping("/api/python/certificate")
    ResponseEntity<String> sendDataToPython(@RequestBody String data);

}
