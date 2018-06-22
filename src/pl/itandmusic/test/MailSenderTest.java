package pl.itandmusic.test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import junit.framework.TestCase;
import pl.itandmusic.mailsender.MailSender;
import pl.itandmusic.mailsender.status.SendingStatus;
import pl.itandmusic.model.MailSenderProperties;

public class MailSenderTest extends TestCase{
	
	private MailSenderProperties mailSenderProperties;
	private MailSender mailSender;
	
	public void setUp() {

		MailSenderProperties.Builder mailSenderPropertiesBuilder = new MailSenderProperties.Builder();
		mailSenderProperties =
				mailSenderPropertiesBuilder
				.setUsername("damianradowieckitest@gmail.com")
				.setPassword("damianradowieckitest123")
				.setFrom("damianradowieckitest@gmail.com")
				.setTo("damianradowieckitest@gmail.com")
				.setSubject("test")
				.setText("test")
				.setSMTPAuth("true")
				.setSMTPHost("smtp.gmail.com")
				.setSMTPPort("587")
				.setSMTPStartTLSEnable("true")
				.build();
		
		mailSender = new MailSender();
	}
	
	@Test
	public void testTryToSendMethod() {
		SendingStatus status = mailSender.tryToSend(mailSenderProperties);
		assertTrue(status.equals(SendingStatus.SENT));
	}
	
	@Test
	public void testSendMethod() throws MessagingException {
		MimeMessage mimeMessage = mailSenderProperties.getMimeMessage();
		mailSender.send(mimeMessage);
	}
	
}
