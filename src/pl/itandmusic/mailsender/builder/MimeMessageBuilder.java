package pl.itandmusic.mailsender.builder;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
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
	private Session session;

	public MimeMessageBuilder(Session session) {
		this.session = session;
	}

	public MimeMessage build() throws AddressException, MessagingException {

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

}
