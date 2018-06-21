package pl.itandmusic.model;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class MailSaverProperties {

	private MailSenderProperties mailSenderProperties;
	private String folderName = "";
	private String storeProtocol = "";
	private String storeHost = "";

	private MailSaverProperties(MailSenderProperties mailSenderProperties, String folderName, String storeProtocol, String storeHost) {
		this.mailSenderProperties = mailSenderProperties;
		this.folderName = folderName;
		this.storeProtocol = storeProtocol;
		this.storeHost = storeHost;
	}

	public String getUsername() {
		return mailSenderProperties.getUsername();
	}

	public String getPassword() {
		return mailSenderProperties.getPassword();
	}

	public String getFrom() {
		return mailSenderProperties.getFrom();
	}

	public String getTo() {
		return mailSenderProperties.getTo();
	}

	public String getText() {
		return mailSenderProperties.getText();
	}

	public String getSubject() {
		return mailSenderProperties.getSubject();
	}

	public Session getSession() {
		return mailSenderProperties.getSession();
	}
	
	public MimeMessage getMimeMessage() {
		return mailSenderProperties.getMimeMessage();
	}

	public String getSmtpAuth() {
		return mailSenderProperties.getSmtpAuth();
	}

	public String getSmtpStartTLSEnable() {
		return mailSenderProperties.getSmtpStartTLSEnable();
	}

	public String getSmtpHost() {
		return mailSenderProperties.getSmtpHost();
	}

	public String getSmtpPort() {
		return mailSenderProperties.getSmtpPort();
	}

	public String getFolderName() {
		return folderName;
	}

	public String getStoreProtocol() {
		return storeProtocol;
	}
	
	public String getStoreHost() {
		return storeHost;
	}


	public static class QuickBuilder {
		private MailSenderProperties mailSenderProperties;
		private String folderName = "";
		private String storeProtocol = "";
		private String storeHost = "";
		
		public MailSaverProperties build() {
			return new MailSaverProperties(mailSenderProperties, folderName, storeProtocol, storeHost);
		}
		
		public QuickBuilder setMailSenerProperties(MailSenderProperties mailSenderProperties) {
			this.mailSenderProperties = mailSenderProperties;
			return this;
		}
		
		public QuickBuilder setFolderName(String folderName) {
			this.folderName = folderName;
			return this;
		}
		
		public QuickBuilder setStoreProtocol(String storeProtocol) {
			this.storeProtocol = storeProtocol;
			return this;
		}
		
		public QuickBuilder setStoreHost(String storeHost) {
			this.storeHost = storeHost;
			return this;
		}
	}
	
	public static class Builder {
		private String username = "";
		private String password = "";
		private String from = "";
		private String to = "";
		private String text = "";
		private String subject = "";

		private String smtpAuth = "true";
		private String smtpStartTLSEnable = "true";
		private String smtpHost = "";
		private String smtpPort = "";

		private String folderName = "";
		private String storeProtocol = "";
		private String storeHost = "";
		
		public MailSaverProperties build() {
			MailSenderProperties.Builder mailSnederPropertiesBuilder = new MailSenderProperties.Builder();
			
			MailSenderProperties mailSenderProperties = mailSnederPropertiesBuilder
			.setFrom(from)
			.setSMTPAuth(smtpAuth)
			.setSMTPHost(smtpHost)
			.setSMTPPort(smtpPort)
			.setSMTPStartTLSEnable(smtpStartTLSEnable)
			.setSubject(subject)
			.setText(text)
			.setTo(to)
			.setUsername(username)
			.setPassword(password)
			.build();
			
			return new MailSaverProperties(mailSenderProperties, folderName, storeProtocol, storeHost);
		}

		public Builder setSMTPHost(String smtpHost) {
			this.smtpHost = smtpHost;
			return this;
		}

		public Builder setSMTPPort(String smtpPort) {
			this.smtpPort = smtpPort;
			return this;
		}

		public Builder setSMTPAuth(String smtpAuth) {
			this.smtpAuth = smtpAuth;
			return this;
		}

		public Builder setSMTPStartTLSEnable(String smtpStartTLSEnable) {
			this.smtpStartTLSEnable = smtpStartTLSEnable;
			return this;
		}

		public Builder setFrom(String from) {
			this.from = from;
			return this;
		}

		public Builder setTo(String to) {
			this.to = to;
			return this;
		}

		public Builder setSubject(String subject) {
			this.subject = subject;
			return this;
		}

		public Builder setText(String text) {
			this.text = text;
			return this;
		}
		
		public Builder setFolderName(String folderName) {
			this.folderName = folderName;
			return this;
		}
		
		public Builder setStoreProtocol(String storeProtocol) {
			this.storeProtocol = storeProtocol;
			return this;
		}
		
		public Builder setStoreHost(String storeHost) {
			this.storeHost = storeHost;
			return this;
		}
	}

}
