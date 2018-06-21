package pl.itandmusic.model;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import pl.itandmusic.mailsender.builder.MimeMessageBuilder;
import pl.itandmusic.mailsender.builder.PropertiesBuilder;

public class MailSenderProperties {
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
	private Session session;
	private MimeMessage mimeMessage;
	private PasswordAuthentication passwordAuthentication;

	private MailSenderProperties(String username, String password, String from, String to, String text, String subject, String smtpAuth, String smtpStartTLSEnable, String smtpHost, String smtpPort) {
		this.username = username;
		this.password = password;
		this.from = from;
		this.to = to;
		this.text = text;
		this.subject = subject;
		this.smtpAuth = smtpAuth;
		this.smtpStartTLSEnable = smtpStartTLSEnable;
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getText() {
		return text;
	}

	public String getSubject() {
		return subject;
	}

	public String getSmtpAuth() {
		return smtpAuth;
	}

	public String getSmtpStartTLSEnable() {
		return smtpStartTLSEnable;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}
	
	public Session getSession() {
		
		if(session == null) {
			PropertiesBuilder propertiesBuilder = new PropertiesBuilder();
	
			Properties properties = propertiesBuilder
					.setSMTPHost(smtpHost)
					.setSMTPPort(smtpPort)
					.setSMTPAuth(smtpAuth)
					.setSMTPStartTLSEnable(smtpStartTLSEnable)
					.build();
			
			passwordAuthentication = new PasswordAuthentication(username, password);
			
			session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return passwordAuthentication;
				}
			});
		}
		
		return session;
	}
	
	public MimeMessage getMimeMessage() {
		
		MimeMessageBuilder mimeMessageBuilder = new MimeMessageBuilder(getSession());
		
		if(mimeMessage == null) {
			try {
				mimeMessage = mimeMessageBuilder
						.setFrom(from)
						.setTo(to)
						.setSubject(subject)
						.setText(text)
						.build();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		return mimeMessage;
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

		public MailSenderProperties build() {
			return new MailSenderProperties(username, password, from, to, text, subject, smtpAuth,
					smtpStartTLSEnable, smtpHost, smtpPort);
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
		
		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
	}

}
