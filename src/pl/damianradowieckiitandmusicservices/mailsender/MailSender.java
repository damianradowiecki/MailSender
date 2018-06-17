package pl.damianradowieckiitandmusicservices.mailsender;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import pl.damianradowieckiitandmusicservices.mailsender.status.SavingInFolderStatus;
import pl.damianradowieckiitandmusicservices.mailsender.status.SendingStatus;
import pl.damianradowieckiitandmusicservices.mailsender.status.Status;

public class MailSender implements Sender {

	@Override
	public SendingStatus send(MimeMessage message) {
		try {
			Transport.send(message);
			return SendingStatus.SENT;
		} catch (MessagingException exception) {
			exception.printStackTrace();
			return SendingStatus.NOT_SENT;
		}
	}

	@Override
	public Status sendAndSaveInFolder(MimeMessage message, String folderName) {
		SendingStatus sendingStatus = send(message);
		SavingInFolderStatus savingInFolderStatus = saveInFolder(folderName);
		Status status = Status.mergeStatuses(sendingStatus, savingInFolderStatus);
		return status;
	}

	private SavingInFolderStatus saveInFolder(String folderName) {
		//TODO implementation
		return SavingInFolderStatus.NOT_SAVED;
	}

}
