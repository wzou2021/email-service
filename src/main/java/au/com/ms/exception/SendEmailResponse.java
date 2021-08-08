package au.com.ms.exception;
import java.util.List;


public class SendEmailResponse 
{
    public SendEmailResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
 
    //General error message about nature of error
    private String message;
    //Specific errors in API request processing
    private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
    
    
    //Getter and setters
    
}