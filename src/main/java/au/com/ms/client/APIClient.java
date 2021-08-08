package au.com.ms.client;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import au.com.ms.exception.ApiResponseHandler;
import au.com.ms.exception.RestTemplateResponseErrorHandler;
import au.com.ms.exception.SendEmailResponse;
import au.com.ms.model.ClientData;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class APIClient {

	@Value("${api.timeout.milliSec}")
	private int apiTimeoutMilliSec;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		restTemplate = this.restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler())
				.setConnectTimeout(Duration.ofMillis(apiTimeoutMilliSec))
				.setReadTimeout(Duration.ofMillis(apiTimeoutMilliSec)).build();
	}

	public String postByJSON(ClientData client) {
		
			client.getHeaders().setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(client.getJson().toString(), client.getHeaders());
			String response = restTemplate.postForObject(client.getApiUrl(), request, String.class);
			return response;
	
	}

	public String postByParam(ClientData client) {
	
			HttpEntity<?> request = new HttpEntity<>(client.getParams(), client.getHeaders());
			String response = restTemplate.postForObject(client.getApiUrl(), request, String.class);
			return response;
		
		
	}

}
