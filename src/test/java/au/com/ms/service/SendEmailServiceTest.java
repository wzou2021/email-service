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
import au.com.ms.data.AbstractTest;
import au.com.ms.exception.SendEmailResponse;
import au.com.ms.model.EmailRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailServiceApplication.class)
@WebAppConfiguration
public class SendEmailServiceTest extends AbstractTest{

	@Autowired
	private SendEmailService sendEmailService;
	
	@Test
	public void testSwitchOver() {
		EmailProvider primary = this.mockFailSGProvider();
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

	

}
