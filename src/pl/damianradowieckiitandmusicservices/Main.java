package pl.damianradowieckiitandmusicservices;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

public class Main {

	public static void main(String[] args) throws AddressException, MessagingException {
		
		MimeMessageBuilder mimeMessageBuilder = new MimeMessageBuilder();
		
		MimeMessage mimeMessage = mimeMessageBuilder
							.setUsername("damianradowieckitest@gmail.com")
							.setPassword("damianradowieckitest123")
							.setFrom("damianradowieckitest@gmail.com")
							.setTo("damianradowiecki@gmail.com")
							.setSMTPHost("smtp.gmail.com")
							.setSMTPPort("587")
							.setSubject("test")
							.setText("testowy email")
							.build();
		
		Sender mailSender = new MailSender();
		
		Status status = mailSender.send(mimeMessage);
			
		System.out.println(status);

	}

}
