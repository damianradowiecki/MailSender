package pl.itandmusic.mailsender;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import pl.itandmusic.mailsaver.MailSaver;
import pl.itandmusic.mailsender.status.SavingInFolderStatus;
import pl.itandmusic.mailsender.status.SendingStatus;
import pl.itandmusic.mailsender.status.Status;
import pl.itandmusic.model.MailSaverProperties;
import pl.itandmusic.model.MailSenderProperties;

public class Main {

	public static void main(String[] args) throws AddressException, MessagingException {

		
		if (args.length == 2) {
			String username = args[0];
			String password = args[1];
			
			MailSenderProperties.Builder mailSenderPropertiesBuilder = new MailSenderProperties.Builder();
			MailSenderProperties mailSenderProperties =
					mailSenderPropertiesBuilder
					.setUsername(username)
					.setPassword(password)
					.setFrom("damianradowieckitest@gmail.com")
					.setTo("damianradowieckitest@gmail.com")
					.setSubject("test")
					.setText("test")
					.setSMTPAuth("true")
					.setSMTPHost("smtp.gmail.com")
					.setSMTPPort("587")
					.setSMTPStartTLSEnable("true")
					.build();
			
			MailSender mailSender = new MailSender();

			SendingStatus status = mailSender.tryToSend(mailSenderProperties);

			System.out.println(status);
		}
		
		else if(args.length == 3) {
			String username = args[0];
			String password = args[1];
			String folderName = args[2];
			
			MailSenderProperties.Builder mailSenderPropertiesBuilder = new MailSenderProperties.Builder();
			MailSenderProperties mailSenderProperties =
					mailSenderPropertiesBuilder
					.setUsername(username)
					.setPassword(password)
					.setFrom("damianradowieckitest@gmail.com")
					.setTo("damianradowieckitest@gmail.com")
					.setSubject("test")
					.setText("test")
					.setSMTPAuth("true")
					.setSMTPHost("smtp.gmail.com")
					.setSMTPPort("587")
					.setSMTPStartTLSEnable("true")
					.build();
			
			MailSender mailSender = new MailSender();

			SendingStatus sendingStatus = mailSender.tryToSend(mailSenderProperties);
			
			MailSaver mailSaver = new MailSaver();
			
			MailSaverProperties.QuickBuilder mailSaverPropertiesQuickBuilder = new MailSaverProperties.QuickBuilder();
			
			MailSaverProperties mailSaverProperties =
					mailSaverPropertiesQuickBuilder
					.setFolderName(folderName)
					.setMailSenerProperties(mailSenderProperties)
					.setStoreProtocol("imaps")
					.setStoreHost("imap.gmail.com")
					.build();
			
			SavingInFolderStatus savingInFolderStatus = mailSaver.tryToSaveInFolder(mailSaverProperties);

			Status status = Status.mergeStatuses(sendingStatus, savingInFolderStatus);
			
			System.out.println(status);
		}

		else {
			System.out.println("You should enter login and password "
					+ "as program aruments or login and password and "
					+ "folder name arguments.");
		}

	}
	

}
