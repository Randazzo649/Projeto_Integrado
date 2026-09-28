package com.sistema.external.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service 
public class EmailSender {
    
    @Autowired private JavaMailSender mailSender;
    private static final String FROM = "erpunithub@gmail.com";

    public void enviarEmail(String emailDestinatario, String assunto, String msg) throws MessagingException{
        MimeMessage email = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(email, "UTF-8");

        helper.setFrom(FROM);
        helper.setTo(emailDestinatario);
        helper.setSubject(assunto);
        helper.setText(msg, true);

        mailSender.send(email);
    }

}
