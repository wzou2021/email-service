package au.com.ms.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import au.com.ms.controller.SendEmailController;
import au.com.ms.model.EmailRequest;
import au.com.ms.service.SendEmailService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailServiceApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
	
protected MockMvc mockMvc;
	
	@Mock
	private SendEmailService sendEmailService;
	
	@InjectMocks
	private SendEmailController sendEmailController;
	
	
	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	public EmailRequest mockRequest() {
		
		EmailRequest request = new EmailRequest();
		List<String> emails = new ArrayList<String>();
		emails.add("wenjingzou@gmail.com");
		request.setFrom("hongbinbiz@gmail.com");
		request.setTo(emails);
		request.setCc("test1@gmail.com");
		request.setBcc("test2@gmail.com");
		request.setText("text");
		request.setSubject("test subject");
		
		return request;
		
	}

}
