package au.com.ms.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import au.com.ms.validator.EmailList;

public class EmailRequest {
	
	@Email(message = "Email from is not valid")
	@NotEmpty(message = "Email from cannot be empty")
	private String from;
	
	@EmailList
	@NotEmpty(message = "Email to cannot be empty")
	private List<String> to;
	
	@Email(message = "Email cc is not valid")
	private String cc;
	
	@Email(message = "Email bcc is not valid")
	private String bcc;
	
	@NotEmpty(message = "Email subject cannot be empty")
	private String subject;
	
	@NotEmpty(message = "Email text cannot be empty")
	private String text;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
