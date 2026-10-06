package com.biblioteca.gp5.email.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	private final JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String email;
	
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendEmail(String to, String subject, String text) {
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
			
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(text, true);
			messageHelper.setFrom(email, "Biblioteca GP5");;
			
			mailSender.send(message);
			
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new MailPreparationException("Erro ao preparar email", e);
			
		}
		
		
	}

}
