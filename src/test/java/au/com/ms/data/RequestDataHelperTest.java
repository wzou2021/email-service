package au.com.ms.data;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import au.com.ms.model.EmailRequest;

public class RequestDataHelperTest extends AbstractTest{
	
	@Test
	public void testConstructSGJson() throws JsonProcessingException {
		EmailRequest request = mockSendGripSuccessRequest();
		String json = RequestDataHelper.ConstructSGApiInputJson(request);
		//TODO, compare with a jsonfile in resource.
		
	}

}
