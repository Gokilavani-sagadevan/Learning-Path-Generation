package com.learning.path.service;

import com.learning.path.dto.EmailDTO;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
    void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException;
    void sendCertificateEmail(String to, byte[] certificatePdf) throws MessagingException;
}