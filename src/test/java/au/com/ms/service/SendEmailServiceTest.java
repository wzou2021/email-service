package au.com.ms.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import au.com.ms.EmailServiceApplication;
import au.com.ms.config.AppProp.EmailProvider;
import au.com.ms.exception.SendEmailResponse;
import au.com.ms.model.EmailRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailServiceApplication.class)
@WebAppConfiguration
public class SendEmailServiceTest{

	@Autowired
	private SendEmailService sendEmailService;
	
	@Test
	public void testSwitchOver() {
		EmailProvider primary = mockFailSGProvider();
		EmailProvider secondary = mockSuccessMGProvider();
		sendEmailService.setEmailProvider(primary, secondary);
		EmailRequest request = this.mockMailGunSuccessRequest();
		ResponseEntity<SendEmailResponse> response = sendEmailService.sendEmail(request);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}
	
	@Test
	public void sendEmailToMailGun_success() {
		EmailRequest request = this.mockMailGunSuccessRequest();
		EmailProvider emailProvider = this.mockSuccessMGProvider();
		
		ResponseEntity<SendEmailResponse> response = sendEmailService.sendEmail(emailProvider, request);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}
	
	@Test
	public void sendEmailToSendGrip_success() {
		EmailRequest request = this.mockSendGripSuccessRequest();
		EmailProvider emailProvider = mockSuccessSGProvider();
		ResponseEntity<SendEmailResponse> response = sendEmailService.sendEmail(emailProvider, request);
		assertTrue(HttpStatus.ACCEPTED.equals(response.getStatusCode()));
	}
	
	@Test
	public void sendEmailToSendGrip_withCCAndBcc_success() {
		EmailRequest request = this.mockSendGripSuccessRequest();
		request.setBcc("test1@gmail.com");
		request.setCc("test2@gmail.com");
		EmailProvider emailProvider = mockSuccessSGProvider();
		ResponseEntity<SendEmailResponse> response = sendEmailService.sendEmail(emailProvider, request);
		assertTrue(HttpStatus.ACCEPTED.equals(response.getStatusCode()));
	}

	private EmailProvider mockSuccessMGProvider() {
		EmailProvider emailProvider = new EmailProvider();
		emailProvider
				.setApiUrl("https://api.mailgun.net/v3/sandbox0467b0ab769544b79ecffccedec0a928.mailgun.org/messages");
		emailProvider.setAuthPassword("9cb667689a7f22244f3d9407ff152a07-64574a68-c04875f3");
		emailProvider.setAuthUsername("api");
		emailProvider.setAuthType("basic");
		emailProvider.setName("mailGun");
		return emailProvider;
	}
	

	private EmailProvider mockFailSGProvider() {
		EmailProvider emailProvider = new EmailProvider();
		emailProvider.setApiUrl("");			
		emailProvider.setAuthType("bearToken");
		emailProvider.setAuthToken("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");
		emailProvider.setName("sendGrip");
		return emailProvider;
	}
	
	private EmailProvider mockSuccessSGProvider() {
		EmailProvider emailProvider = new EmailProvider();
		emailProvider.setApiUrl("https://api.sendgrid.com/v3/mail/send");			
		emailProvider.setAuthType("bearToken");
		emailProvider.setAuthToken("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");
		emailProvider.setName("sendGrip");
		return emailProvider;
	}
	private EmailRequest mockSendGripSuccessRequest() {
		EmailRequest request = new EmailRequest();
		List<String> toEmails = new ArrayList<String>();
		toEmails.add("wenjingzou@gmail.com");
		request.setTo(toEmails);
		request.setFrom("hongbinbiz@gmail.com");
		request.setText("Mail gun");
		request.setSubject("test Mail Gun");
		return request;			
	}

	public EmailRequest mockMailGunSuccessRequest() {

		EmailRequest request = new EmailRequest();
		List<String> toEmails = new ArrayList<String>();
		toEmails.add("hongbinbiz@gmail.com");
		request.setTo(toEmails);
		request.setFrom("wenjingzou@gmail.com");
		request.setText("Mail gun");
		request.setSubject("test Mail Gun");

		return request;

	}

}
