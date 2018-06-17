package pl.damianradowieckiitandmusicservices;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MimeMessageBuilder {

	private MimeMessage mimeMessage;
	private String from = "";
	private String to = "";
	private String text = "";
	private String subject = "";
	private String username = "";
	private String password = "";
	private String smtpAuth = "true";
	private String smtpStartTLSEnable = "true";
	private String smtpHost = "";
	private String smtpPort = "";

	public MimeMessage build() throws AddressException, MessagingException {

		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", smtpAuth);
		properties.put("mail.smtp.starttls.enable", smtpStartTLSEnable);
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		mimeMessage = new MimeMessage(session);

		mimeMessage.setFrom(new InternetAddress(from));
		mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(to));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(text);

		return this.mimeMessage;
	}

	public MimeMessageBuilder setFrom(String from) {
		this.from = from;
		return this;
	}

	public MimeMessageBuilder setTo(String to) {
		this.to = to;
		return this;
	}

	public MimeMessageBuilder setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public MimeMessageBuilder setText(String text) {
		this.text = text;
		return this;
	}

	public MimeMessageBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public MimeMessageBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public MimeMessageBuilder setSMTPHost(String smtpHost) {
		this.smtpHost = smtpHost;
		return this;
	}

	public MimeMessageBuilder setSMTPPort(String smtpPort) {
		this.smtpPort = smtpPort;
		return this;
	}

	public MimeMessageBuilder setSMTPAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
		return this;
	}

	public MimeMessageBuilder setSMTPStartTLSEnable(String smtpStartTLSEnable) {
		this.smtpStartTLSEnable = smtpStartTLSEnable;
		return this;
	}
}
