package com.lab08.emailservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    @Value("${mail.from:no-reply@lab8.local}")
    private String fromAddress;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSuccessEmail(String to, String userName, String fileName, String fileUrl) {
        String subject = "[Lab8] File upload success";
        String body = "Hello " + userName + ",\n\n"
                + "Your file was uploaded successfully.\n"
                + "File name: " + fileName + "\n"
                + "File URL: " + fileUrl + "\n\n"
                + "Regards,\nSOA Lab 8";

        sendEmail(to, subject, body);
    }

    public void sendFailEmail(String to, String userName, String fileName, String reason) {
        String subject = "[Lab8] File upload failed";
        String body = "Hello " + userName + ",\n\n"
                + "Your file upload failed.\n"
                + "File name: " + fileName + "\n"
                + "Reason: " + reason + "\n\n"
                + "Regards,\nSOA Lab 8";

        sendEmail(to, subject, body);
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
