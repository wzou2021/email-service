package au.com.ms.model;

import javax.validation.constraints.Email;

public class EmailAddress {
	
	@Email(message = "Email from is not valid")
	private String email;
	
	public EmailAddress(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
