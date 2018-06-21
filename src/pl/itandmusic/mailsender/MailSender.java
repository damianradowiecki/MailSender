package pl.itandmusic.mailsender;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import pl.itandmusic.mailsender.status.SendingStatus;
import pl.itandmusic.model.MailSenderProperties;

public class MailSender implements Sender {

	@Override
	public void send(MimeMessage message) throws MessagingException {
			Transport.send(message);
	}
	
	
	@Override
	public SendingStatus tryToSend(MimeMessage message) {
		try {
			send(message);
			return SendingStatus.SENT;
		} catch (MessagingException exception) {
			exception.printStackTrace();
			return SendingStatus.NOT_SENT;
		}
	}

	@Override
	public void send(MailSenderProperties mailSenderProperites) throws MessagingException {
		MimeMessage mimeMessage = mailSenderProperites.getMimeMessage();
		send(mimeMessage);
	}

	@Override
	public SendingStatus tryToSend(MailSenderProperties mailSenderProperites) {
		try {
			send(mailSenderProperites);
			return SendingStatus.SENT;
		}
		catch(Exception e) {
			e.printStackTrace();
			return SendingStatus.NOT_SENT;
		}
	}

	

}
