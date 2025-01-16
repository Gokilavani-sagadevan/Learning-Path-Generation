package com.learning.path.service.impl;

import com.learning.path.dto.CertificateDTO;
import com.learning.path.model.Certificate;
import com.learning.path.model.User;
import com.learning.path.model.LearningPath;
import com.learning.path.repository.CertificateRepository;
import com.learning.path.service.CertificateService;
import com.learning.path.service.UserService;
import com.learning.path.service.LearningPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LearningPathService learningPathService;

    @Override
    public List<CertificateDTO> getUserCertificates(Long userId) {
        List<Certificate> certificates = certificateRepository.findByUserId(userId);
        return certificates.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CertificateDTO convertToDTO(Certificate certificate) {
        CertificateDTO dto = new CertificateDTO();
        dto.setId(certificate.getId());
        dto.setCertificateNumber(certificate.getCertificateNumber());
        dto.setUserId(certificate.getUser().getId());
        dto.setLearningPathId(certificate.getLearningPath().getId());
        dto.setIssueDate(certificate.getIssueDate());
        dto.setStatus(certificate.getStatus());
        return dto;
    }

    @Override
    public Certificate generateCertificate(Long userId, Long learningPathId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LearningPath learningPath = learningPathService.getLearningPathById(learningPathId)
                .orElseThrow(() -> new RuntimeException("Learning Path not found"));

        // Check if certificate already exists
        Optional<Certificate> existingCert = certificateRepository
                .findByUserIdAndLearningPathId(userId, learningPathId);
        if (existingCert.isPresent()) {
            return existingCert.get();
        }

        Certificate certificate = new Certificate();
        certificate.setUser(user);
        certificate.setLearningPath(learningPath);
        certificate.setCertificateNumber(UUID.randomUUID().toString());
        certificate.setIssueDate(LocalDateTime.now());
        certificate.setStatus("ISSUED");

        return certificateRepository.save(certificate);
    }

    @Override
    public Optional<Certificate> getCertificateById(Long id) {
        return certificateRepository.findById(id);
    }


    @Override
    public Certificate verifyCertificate(String certificateNumber) {
        return certificateRepository.findByCertificateNumber(certificateNumber)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    @Override
    public byte[] downloadCertificate(Long certificateId) {
        Certificate certificate = getCertificateById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        // Generate PDF certificate
        // This is a placeholder - implement actual PDF generation logic
        return generatePdfCertificate(certificate);
    }

    private byte[] generatePdfCertificate(Certificate certificate) {
        // Implement PDF generation logic here
        // You might want to use a library like iText or Apache PDFBox
        return new byte[0]; // Placeholder
    }
}