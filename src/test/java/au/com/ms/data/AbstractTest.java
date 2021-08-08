package au.com.ms.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.ms.EmailServiceApplication;
import au.com.ms.config.AppProp.EmailProvider;
import au.com.ms.model.EmailRequest;


public abstract class AbstractTest {
	


	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	public EmailProvider mockSuccessMGProvider() {
		EmailProvider emailProvider = new EmailProvider();
		emailProvider
				.setApiUrl("https://api.mailgun.net/v3/sandbox0467b0ab769544b79ecffccedec0a928.mailgun.org/messages");
		emailProvider.setAuthPassword("9cb667689a7f22244f3d9407ff152a07-64574a68-c04875f3");
		emailProvider.setAuthUsername("api");
		emailProvider.setAuthType("basic");
		emailProvider.setName("mailGun");
		return emailProvider;
	}
	

	public EmailProvider mockFailSGProvider() {
		EmailProvider emailProvider = new EmailProvider();
		emailProvider.setApiUrl("");			
		emailProvider.setAuthType("bearToken");
		emailProvider.setAuthToken("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");
		emailProvider.setName("sendGrip");
		return emailProvider;
	}
	
	public EmailProvider mockSuccessSGProvider() {
		EmailProvider emailProvider = new EmailProvider();
		emailProvider.setApiUrl("https://api.sendgrid.com/v3/mail/send");			
		emailProvider.setAuthType("bearToken");
		emailProvider.setAuthToken("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");
		emailProvider.setName("sendGrip");
		return emailProvider;
	}
	public EmailRequest mockSendGripSuccessRequest() {
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
