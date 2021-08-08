package au.com.ms.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import au.com.ms.data.AbstractTest;
import au.com.ms.model.EmailAddress;
import au.com.ms.model.EmailRequest;

public class SendEmailControllerIntegrationTest extends AbstractTest {


	@Test
	public void sendEmailTest() {
		try {
			String url = "http://localhost:8070/api/v1/sendEmail";
			
			EmailRequest request = new EmailRequest();
			request.setFrom("wenjingzou@gmail.com");
			List<EmailAddress> list = new ArrayList<EmailAddress>();
			list.add(new EmailAddress("hongbinbiz@gmail.com"));
			request.setCc("test1@gmail.com");
			request.setBcc("test2@gmail.com");
			request.setText("text");
			request.setSubject("test subject");
			String inputJson = mapToJson(request);
			MvcResult mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
					.andReturn();
			MockHttpServletResponse response = mvcResult.getResponse();
			System.out.println(response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
