package pl.damianradowieckiitandmusicservices;

import javax.mail.internet.MimeMessage;

public interface Sender {	
	Status send(MimeMessage message);
	Status sendAndSaveInFolder(MimeMessage message, String folderName);
}
