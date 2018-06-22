package pl.itandmusic.test;

import javax.mail.MessagingException;

import org.junit.Test;

import junit.framework.TestCase;
import pl.itandmusic.mailsaver.MailSaver;
import pl.itandmusic.mailsender.status.SavingInFolderStatus;
import pl.itandmusic.model.MailSaverProperties;
import pl.itandmusic.model.MailSenderProperties;

public class MailSaverTest extends TestCase {

	private MailSenderProperties mailSenderProperties;
	private MailSaverProperties mailSaverProperties;
	private MailSaver mailSaver;

	public void setUp() {
		MailSenderProperties.Builder mailSenderPropertiesBuilder = new MailSenderProperties.Builder();

		mailSenderProperties = mailSenderPropertiesBuilder
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

		mailSaver = new MailSaver();

		MailSaverProperties.QuickBuilder mailSaverPropertiesQuickBuilder = new MailSaverProperties.QuickBuilder();

		mailSaverProperties = mailSaverPropertiesQuickBuilder
				.setFolderName("[Gmail]/Sent Mail")
				.setMailSenerProperties(mailSenderProperties)
				.setStoreProtocol("imaps")
				.setStoreHost("imap.gmail.com")
				.build();
	}
	
	@Test
	public void testTryToSaveInFolder() {
		SavingInFolderStatus savingInFolderStatus = mailSaver.tryToSaveInFolder(mailSaverProperties);
		assertTrue(savingInFolderStatus.equals(SavingInFolderStatus.SAVED));
	}
	
	@Test
	public void testSaveInFolder() throws MessagingException {
		mailSaver.saveInFolder(mailSaverProperties);
	}
}
