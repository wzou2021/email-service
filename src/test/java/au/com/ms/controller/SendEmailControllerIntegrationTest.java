package au.com.ms.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import au.com.ms.data.AbstractTest;
import au.com.ms.exception.SendEmailResponse;
import au.com.ms.model.EmailRequest;
import au.com.ms.service.SendEmailService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SendEmailController.class)
@WebAppConfiguration
public class SendEmailControllerIntegrationTest extends AbstractTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SendEmailService service;

	@Test
	public void sendEmailControllerTest_success() throws Exception {

		EmailRequest request = this.mockMailGunSuccessRequest();// Mockito.mock(EmailRequest.class);
		SendEmailResponse sendEmailResponse = new SendEmailResponse("success", null);
		ResponseEntity<SendEmailResponse> response = new ResponseEntity<SendEmailResponse>(sendEmailResponse,
				HttpStatus.OK);
		Mockito.when(service.sendEmail(request)).thenReturn(response);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/sendEmail")
				.accept(MediaType.APPLICATION_JSON).content(this.mapToJson(request))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

}
