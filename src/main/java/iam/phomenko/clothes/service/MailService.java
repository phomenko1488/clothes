package iam.phomenko.clothes.service;

public interface MailService {
    void sendMail(String receiver, String subject, String text);
}
