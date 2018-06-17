package pl.damianradowieckiitandmusicservices;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class MailSender implements Sender {

	@Override
	public Status send(MimeMessage message) {
		try {
	         Transport.send(message);
	         return Status.SENT;
	      } catch (MessagingException exception) {
	    	  exception.printStackTrace();
	    	  return Status.NOT_SENT;
	      }
	}

	@Override
	public Status sendAndSaveInFolder(MimeMessage message, String folderName) {
		// TODO Auto-generated method stub
		return null;
	}

}
