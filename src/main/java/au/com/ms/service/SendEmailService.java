package au.com.ms.service;

import org.springframework.http.ResponseEntity;

import au.com.ms.config.AppProp.EmailProvider;
import au.com.ms.exception.SendEmailResponse;
import au.com.ms.model.EmailRequest;

public interface SendEmailService {
	public abstract ResponseEntity<SendEmailResponse> sendEmail(EmailRequest request);
	public abstract ResponseEntity<SendEmailResponse> sendEmail(EmailProvider emailProvider, EmailRequest request);
}
	