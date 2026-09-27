package com.sistema.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service 
public class EmailSender {
    
    @Autowired private JavaMailSender mailSender;
    private static final String FROM = "erpunithub@gmail.com";

    public void enviarEmail(String emailDestinatario, String assunto, String msg){
        SimpleMailMessage email = new SimpleMailMessage();

        email.setFrom(FROM);
        email.setTo(emailDestinatario);
        email.setSubject(assunto);
        email.setText(msg);

        mailSender.send(email);
    }

}
