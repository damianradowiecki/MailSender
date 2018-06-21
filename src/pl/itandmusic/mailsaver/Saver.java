package pl.itandmusic.mailsaver;

import javax.mail.MessagingException;

import pl.itandmusic.mailsender.status.SavingInFolderStatus;
import pl.itandmusic.model.MailSaverProperties;

public interface Saver {
	SavingInFolderStatus tryToSaveInFolder(MailSaverProperties mailSaverProperties);
	void saveInFolder(MailSaverProperties mailSaverProperties) throws MessagingException;
}
