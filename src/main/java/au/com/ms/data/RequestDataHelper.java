package au.com.ms.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.ms.model.EmailRequest;
import au.com.ms.model.SGJson;
import au.com.ms.model.SGJson.Content;
import au.com.ms.model.SGJson.Email;
import au.com.ms.model.SGJson.Personalizations;
import au.com.ms.util.AppUtils;

public class RequestDataHelper {

	public static MultiValueMap<String, String> ConstructMGApiInputMap(EmailRequest request) {

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("to", request.getTo().get(0));
		map.add("subject", request.getSubject());
		if (!AppUtils.isNullOrEmpty(request.getCc())) {
			map.add("cc", request.getCc());
		}
		if (!AppUtils.isNullOrEmpty(request.getBcc())) {
			map.add("bcc", request.getBcc());
		}
		map.add("text", request.getText());
		map.add("from", request.getFrom());
		return map;
	}

	public static String ConstructSGApiInputJson(EmailRequest request) throws JsonProcessingException {
		SGJson json = new SGJson();
		json.setFrom(new Email(request.getFrom()));

		List<Content> contents = new ArrayList<Content>();
		contents.add(new Content(request.getSubject(), request.getText()));
		json.setContent(contents);

		List<Personalizations> personalizationsList = new ArrayList<SGJson.Personalizations>();
		Personalizations personalizations = new Personalizations();
		personalizations.setSubject(request.getSubject());
	
		personalizations.setTo(getEmailListForTo(request.getTo()));
		if(!AppUtils.isNullOrEmpty(request.getCc())) {
			personalizations.setCc(getEmailList(request.getCc()));
		}
		if(!AppUtils.isNullOrEmpty(request.getBcc())) {
			personalizations.setBcc(getEmailList(request.getBcc()));
		}
		
		personalizationsList.add(personalizations);
		json.setPersonalizations(personalizationsList);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			String jsonString = mapper.writeValueAsString(json);
			return jsonString;
		} catch (JsonProcessingException e) {
			throw e;
		}
	}

	private static List<Email> getEmailListForTo(List<String> emailsInRequest) {
		List<Email> emails = new ArrayList<Email>();

		emailsInRequest.forEach(string -> {
			Email email = new Email(string);
			emails.add(email);
		});
		return emails;
	}

	public static List<Email> getEmailList(String emailsInRequest) {

		List<Email> emails = new ArrayList<Email>();

		List<String> splittedemailsInRequest = Arrays.asList(emailsInRequest);

		splittedemailsInRequest.forEach(string -> {
			Email email = new Email(string);
			emails.add(email);
		});
		return emails;
	}

}
