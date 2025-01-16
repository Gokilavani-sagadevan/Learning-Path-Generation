package com.learning.path.controller;

import com.learning.path.model.Certificate;
import com.learning.path.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.learning.path.dto.CertificateDTO;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin(origins = "http://localhost:3000")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping("/generate/{userId}/{learningPathId}")
    public ResponseEntity<Certificate> generateCertificate(
            @PathVariable Long userId,
            @PathVariable Long learningPathId) {
        return ResponseEntity.ok(certificateService.generateCertificate(userId, learningPathId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long id) {
        return certificateService.getCertificateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CertificateDTO>> getUserCertificates(@PathVariable Long userId) {
        return ResponseEntity.ok(certificateService.getUserCertificates(userId));
    }

    @GetMapping("/verify/{certificateNumber}")
    public ResponseEntity<Certificate> verifyCertificate(@PathVariable String certificateNumber) {
        return ResponseEntity.ok(certificateService.verifyCertificate(certificateNumber));
    }

    @GetMapping("/download/{certificateId}")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long certificateId) {
        byte[] certificateBytes = certificateService.downloadCertificate(certificateId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "certificate.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(certificateBytes);
    }
}