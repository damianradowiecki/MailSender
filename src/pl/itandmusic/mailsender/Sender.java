package pl.itandmusic.mailsender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import pl.itandmusic.mailsender.status.SendingStatus;
import pl.itandmusic.model.MailSenderProperties;

public interface Sender {	
	void send(MimeMessage message) throws MessagingException;
	SendingStatus tryToSend(MimeMessage message);
	void send(MailSenderProperties mailSenderProperites) throws MessagingException;
	SendingStatus tryToSend(MailSenderProperties mailSenderProperites);
}
