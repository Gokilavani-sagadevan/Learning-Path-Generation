package com.learning.path.repository;

import com.learning.path.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByUserId(Long userId);
    List<Certificate> findByLearningPathId(Long learningPathId);
    Optional<Certificate> findByCertificateNumber(String certificateNumber);
    Optional<Certificate> findByUserIdAndLearningPathId(Long userId, Long learningPathId);
}