package au.com.ms.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import au.com.ms.data.AppConstants;

public class ApiResponseHandler {

	public static ResponseEntity<SendEmailResponse> getSuccessResponse(String providerName, HttpStatus status) {
		String successMessage = String.format("Send Email %s via %s", AppConstants.RESPONSE_SUCCESS, providerName);
		SendEmailResponse response = new SendEmailResponse(successMessage, null);
		return new ResponseEntity<>(response, status);
	}

	public static ResponseEntity<SendEmailResponse> getFailedResponse(Exception e) {
		List<String> details = new ArrayList<String>();
		details.add(e.getMessage());
		SendEmailResponse response = new SendEmailResponse(AppConstants.RESPONSE_FAILED, details);
		if(e instanceof AuthenticationException) {
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static ResponseEntity<SendEmailResponse> getFailedResponse(String error) {
		List<String> details = new ArrayList<String>();
		details.add(error);
		SendEmailResponse response = new SendEmailResponse(AppConstants.RESPONSE_FAILED, details);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static ResponseEntity<SendEmailResponse> getResponseFromAPI(ResponseEntity<String> response,String providerName) {
		// Assume it is successful
		if (response.getStatusCode().equals(HttpStatus.ACCEPTED) || response.getStatusCode().equals(HttpStatus.OK)) {
			return getSuccessResponse(providerName,response.getStatusCode());
		}

		List<String> details = new ArrayList<String>();
		details.add(response.getBody());
		SendEmailResponse sendEmailResponse = new SendEmailResponse(AppConstants.RESPONSE_FAILED, details);
		return new ResponseEntity<>(sendEmailResponse, response.getStatusCode());
	}
}
