package au.com.ms.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ClientData {
	
	private String apiUrl;
	private String json;
	private MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	private HttpHeaders headers;
	
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	public MultiValueMap<String, String> getParams() {
		return params;
	}
	public void setParams(MultiValueMap<String, String> params) {
		this.params = params;
	}
	public HttpHeaders getHeaders() {
		return headers;
	}
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

}
