package com.sgagestudio.demo.document_reminder.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class GmailSenderService {

    private final JavaMailSender javaMailSender;

    public GmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String fromAddress,
                         String fromDisplayName,
                         String to,
                         String subject,
                         String htmlContent,
                         String language) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromAddress, fromDisplayName);
            helper.setTo(to);
            helper.setSubject(subject == null ? getSubjectByLanguage(language) : subject);
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }

    private String getSubjectByLanguage(String language) {
        if (language == null) {
            return defaultSubject();
        }

        return switch (language.toLowerCase()) {
            case "es", "español", "spanish" ->
                    "Informe de Auditoría IA Express – Oportunidades de automatización en tu empresa";
            case "en", "english" ->
                    "AI Audit Report – Opportunities to automate and optimize your business";
            default -> defaultSubject();
        };
    }

    private String defaultSubject() {
        return "AI Audit Report – Opportunities to automate and optimize your business";
    }
}
