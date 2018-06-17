package pl.damianradowieckiitandmusicservices.mailsender;

import javax.mail.internet.MimeMessage;

import pl.damianradowieckiitandmusicservices.mailsender.status.SendingStatus;
import pl.damianradowieckiitandmusicservices.mailsender.status.Status;

public interface Sender {	
	SendingStatus send(MimeMessage message);
	Status sendAndSaveInFolder(MimeMessage message, String folderName);
}
