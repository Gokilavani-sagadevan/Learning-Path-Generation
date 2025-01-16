package com.learning.path.service;

import com.learning.path.dto.CertificateDTO;
import com.learning.path.model.Certificate;
import java.util.List;
import java.util.Optional;

public interface CertificateService {
    Certificate generateCertificate(Long userId, Long learningPathId);
    Optional<Certificate> getCertificateById(Long id);
    List<CertificateDTO> getUserCertificates(Long userId);
    Certificate verifyCertificate(String certificateNumber);
    byte[] downloadCertificate(Long certificateId);
}