package com.trackorjargh.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.trackorjargh.javaclass.User;

@Component
public class MailConfigurer {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
    
    public void sendSimpleMimeMessage(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
           
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            
            emailSender.send(message);
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }
    
    public void sendVerificationEmail(User user) {
    		String to = user.getEmail();
    		String subject = "Verificación del usuario " + user.getName() + " en TrackOrJargh";
    		String message = "<body><p>Por favor pinche en este <a href=''>enlace</a> para poder usar todas las ventajas de TrackOrJack</p></body>";
    		
    		sendSimpleMimeMessage(to, subject, message);
    }
    
    
}
