package au.com.ms.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import au.com.ms.client.APIClient;
import au.com.ms.config.AppProp;
import au.com.ms.config.AppProp.EmailProvider;
import au.com.ms.data.AppConstants;
import au.com.ms.data.RequestDataHelper;
import au.com.ms.exception.ApiResponseHandler;
import au.com.ms.exception.SendEmailResponse;
import au.com.ms.model.ClientData;
import au.com.ms.model.EmailRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SendEmailServiceImpl implements SendEmailService {

	@Autowired
	private AppProp appProp;

	@Autowired
	private APIClient apiClient;

	private EmailProvider primaryProvider;
	private EmailProvider secondaryProvider;

	@PostConstruct
	public void init() {
		if (appProp.getEmailProvider1().getPrimary()) {
			primaryProvider = appProp.getEmailProvider1();
			secondaryProvider = appProp.getEmailProvider2();
		} else if (appProp.getEmailProvider2().getPrimary()) {
			primaryProvider = appProp.getEmailProvider2();
			secondaryProvider = appProp.getEmailProvider1();
		}
	}

	@Override
	public ResponseEntity<SendEmailResponse> sendEmail(EmailRequest request) {
		ResponseEntity<SendEmailResponse> response = sendEmail(primaryProvider, request);
		if (response.getStatusCode().equals(HttpStatus.OK) || response.getStatusCode().equals(HttpStatus.ACCEPTED)) {
			return response;
		}
		//LOG here
		response = sendEmail(secondaryProvider, request);
		return response;
	}

	@Override
	public ResponseEntity<SendEmailResponse> sendEmail(EmailProvider provider, EmailRequest request) {
		// get default client data
		ClientData clientData = new ClientData();
		clientData.setHeaders(getHttpHeaders(provider));
		clientData.setApiUrl(provider.getApiUrl());
		try {
			if (AppConstants.EMAIL_PROVIDER_SEND_GRIP.equalsIgnoreCase(provider.getName())) {
				String json = RequestDataHelper.ConstructSGApiInputJson(request);
				clientData.setJson(json);
				String response = apiClient.postByJSON(clientData);
				return ApiResponseHandler.getResponseFromAPI(new ResponseEntity<String>(response, HttpStatus.ACCEPTED), provider.getName());
			} else if (AppConstants.EMAIL_PROVIDER_MAIL_GUN.equalsIgnoreCase(provider.getName())) {
				MultiValueMap<String, String> map = RequestDataHelper.ConstructMGApiInputMap(request);
				clientData.setParams(map);
				String response = apiClient.postByParam(clientData);
				return ApiResponseHandler.getResponseFromAPI(new ResponseEntity<String>(response, HttpStatus.OK), provider.getName());
			} else {
				return ApiResponseHandler.getFailedResponse(AppConstants.RESPONSE_UNKNOWN_PROVIDER);
			}
		} catch (Exception e) {
			//TODO: do logging here
			return ApiResponseHandler.getFailedResponse(e);
		}
	}

	// TODO: can be extracted to another class Authentication Handler
	private HttpHeaders getHttpHeaders(EmailProvider provider) {
		HttpHeaders headers = new HttpHeaders();
		if ("basic".equals(provider.getAuthType())) {
			headers.setBasicAuth(provider.getAuthUsername(), provider.getAuthPassword());
		} else if ("bearToken".equals(provider.getAuthType())) {
			headers.setBearerAuth(provider.getAuthToken());
		}
		return headers;
	}

	@Override
	public void setEmailProvider(EmailProvider primary, EmailProvider secondary) {
		this.primaryProvider = primary;
		this.secondaryProvider = secondary;
	}

}
