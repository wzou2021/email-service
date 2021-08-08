package au.com.ms.exception;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.ResponseErrorHandler;

import au.com.ms.util.AppUtils;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		//TODO: extract original response from API
		String apiResponse = getResponseBody( httpResponse);
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			String response = "Internal Server Error";
			if(!AppUtils.isNullOrEmpty(apiResponse)){
				response += String.format("API ERROR[%s]", apiResponse);
			}
			throw new InternalServerErrorException(response);
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			// handle CLIENT_ERROR
			if (httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				String response = "Failed Authentication";
				if(!AppUtils.isNullOrEmpty(apiResponse)){
					response += String.format("API ERROR[%s]", apiResponse);
				}
				throw new AuthenticationException(response);
			} else {
				String response = "Bad Request";
				if(!AppUtils.isNullOrEmpty(apiResponse)){
					response += String.format("API ERROR[%s]", apiResponse);
				}
				throw new BadRequestException(response);
			}
		}
	}
	
	private String getResponseBody(ClientHttpResponse response) {
		try {
	        InputStream responseBody = response.getBody();
	        if (responseBody != null) {
	        	byte[] result =  FileCopyUtils.copyToByteArray(responseBody);
	        	if(result!= null) {
	        		return result.toString();
	        	}
	        }
	    } catch (IOException ex) {
	        // ignore
	    }
	    return "";
	}

}