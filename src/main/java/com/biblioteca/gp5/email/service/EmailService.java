package com.biblioteca.gp5.email.service;

import org.springframework.stereotype.Service;

import com.biblioteca.gp5.exception.email.MailSendException;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;

@Service
public class EmailService {
	
	private final Resend resend;
	
	public EmailService(Resend resend) {
		this.resend = resend;
	}
	
	public void sendEmail(String to, String subject, String text) {
		
		try {
			CreateEmailOptions params = CreateEmailOptions.builder()
					.from("Biblioteca GP5 <onboarding@resend.dev>")
					.to(to)
					.subject(subject)
					.html(text)
					.build();
			
			resend.emails().send(params);
			
		} catch (ResendException e) {
			throw new MailSendException("Erro ao enviar e-mail", e);
			
		}	
	}

}
