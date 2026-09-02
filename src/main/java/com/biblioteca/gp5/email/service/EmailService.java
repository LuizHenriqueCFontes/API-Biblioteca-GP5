package com.biblioteca.gp5.email.service;

import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	private final JavaMailSender mailSender;
	
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
			
			mailSender.send(message);
			
		} catch (MessagingException e) {
			throw new MailPreparationException("Erro ao preparar email", e);
			
		}
		
		
	}

}
