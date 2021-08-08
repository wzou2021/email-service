package au.com.ms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:email.properties")
public class AppProp {

	private EmailProvider1 emailProvider1;
	private EmailProvider2 emailProvider2;

	public EmailProvider1 getEmailProvider1() {
		return emailProvider1;
	}

	public void setEmailProvider1(EmailProvider1 emailProvider1) {
		this.emailProvider1 = emailProvider1;
	}

	public EmailProvider2 getEmailProvider2() {
		return emailProvider2;
	}

	public void setEmailProvider2(EmailProvider2 emailProvider2) {
		this.emailProvider2 = emailProvider2;
	}

	public static class EmailProvider {
		private Boolean primary;
		private String name;
		private String authType;
		private String authUsername;
		private String authPassword;
		private String authToken;
		private String apiUrl;
		private String jsonBody;
		
		public Boolean getPrimary() {
			return primary;
		}
		public void setPrimary(Boolean primary) {
			this.primary = primary;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAuthType() {
			return authType;
		}
		public void setAuthType(String authType) {
			this.authType = authType;
		}
		public String getAuthUsername() {
			return authUsername;
		}
		public void setAuthUsername(String authUsername) {
			this.authUsername = authUsername;
		}
		public String getAuthPassword() {
			return authPassword;
		}
		public void setAuthPassword(String authPassword) {
			this.authPassword = authPassword;
		}
		public String getAuthToken() {
			return authToken;
		}
		public void setAuthToken(String authToken) {
			this.authToken = authToken;
		}
		public String getApiUrl() {
			return apiUrl;
		}
		public void setApiUrl(String apiUrl) {
			this.apiUrl = apiUrl;
		}
		public String getJsonBody() {
			return jsonBody;
		}
		public void setJsonBody(String jsonBody) {
			this.jsonBody = jsonBody;
		}
	}

	public static class EmailProvider1 extends EmailProvider {

	}

	public static class EmailProvider2 extends EmailProvider {

	}
}