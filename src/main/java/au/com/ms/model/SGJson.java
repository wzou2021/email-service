package au.com.ms.model;

import java.util.List;

public class SGJson {
	
	private List<Personalizations> personalizations;
	private Email from;
	private List<Content> content;
	
	public List<Personalizations> getPersonalizations() {
		return personalizations;
	}
	public void setPersonalizations(List<Personalizations> personalizations) {
		this.personalizations = personalizations;
	}

	public Email getFrom() {
		return from;
	}
	public void setFrom(Email from) {
		this.from = from;
	}
	public List<Content> getContent() {
		return content;
	}
	public void setContent(List<Content> content) {
		this.content = content;
	}

	public static class Content{
		private String type;
		private String value;
		public Content(String type, String value) {
			this.type = type;
			this.value = value;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public static class Personalizations{
		private List<Email> to;
		private List<Email> cc;
		private List<Email> bcc;
		private String subject;
	
		public List<Email> getTo() {
			return to;
		}
		public void setTo(List<Email> to) {
			this.to = to;
		}
		public List<Email> getCc() {
			return cc;
		}
		public void setCc(List<Email> cc) {
			this.cc = cc;
		}
		public List<Email> getBcc() {
			return bcc;
		}
		public void setBcc(List<Email> bcc) {
			this.bcc = bcc;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
	}
	public static class Email {
		
		private String email;
		public Email(String email) {
			this.email = email;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	}
	public static class From {

		private Email email;

		public From(Email email) {
			this.email = email;
		}

		public Email getEmail() {
			return email;
		}

		public void setEmail(Email email) {
			this.email = email;
		}

	}

}
