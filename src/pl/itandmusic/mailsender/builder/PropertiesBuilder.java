package pl.itandmusic.mailsender.builder;

import java.util.Properties;

public class PropertiesBuilder {
	private String smtpAuth = "true";
	private String smtpStartTLSEnable = "true";
	private String smtpHost = "";
	private String smtpPort = "";
	
	
	public Properties build() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", smtpAuth);
		properties.put("mail.smtp.starttls.enable", smtpStartTLSEnable);
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		return properties;
	}
	
	public PropertiesBuilder setSMTPHost(String smtpHost) {
		this.smtpHost = smtpHost;
		return this;
	}
	
	public PropertiesBuilder setSMTPPort(String smtpPort) {
		this.smtpPort = smtpPort;
		return this;
	}

	public PropertiesBuilder setSMTPAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
		return this;
	}

	public PropertiesBuilder setSMTPStartTLSEnable(String smtpStartTLSEnable) {
		this.smtpStartTLSEnable = smtpStartTLSEnable;
		return this;
	}
}
