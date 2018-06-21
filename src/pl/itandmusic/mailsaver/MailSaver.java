package pl.itandmusic.mailsaver;

import javax.mail.Folder;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import pl.itandmusic.mailsender.status.SavingInFolderStatus;
import pl.itandmusic.model.MailSaverProperties;

public class MailSaver implements Saver {

	@Override
	public SavingInFolderStatus tryToSaveInFolder(MailSaverProperties mailSaverProperties) {
		try {
			saveInFolder(mailSaverProperties);
			return SavingInFolderStatus.SAVED;
		}catch(FolderNotFoundException exception){
			System.out.println(exception.getMessage());
			showAllFoldersOnServer(mailSaverProperties);
			return SavingInFolderStatus.NOT_SAVED;
		}
		catch(IllegalStateException e) {
			return SavingInFolderStatus.SAVED;
		}
		catch (MessagingException e) {
			e.printStackTrace();
			return SavingInFolderStatus.NOT_SAVED;
		}
	}

	@Override
	public void saveInFolder(MailSaverProperties mailSaverProperties) throws MessagingException {
		Session session = mailSaverProperties.getSession();
		MimeMessage mimeMessage = mailSaverProperties.getMimeMessage();
		String folderName = mailSaverProperties.getFolderName();
		String username = mailSaverProperties.getUsername();
		String password = mailSaverProperties.getPassword();
		String storeHost = mailSaverProperties.getStoreHost();
		
		Store store = session.getStore(mailSaverProperties.getStoreProtocol());
		store.connect(storeHost, username, password);
		Folder folder = store.getFolder(folderName);
		folder.appendMessages(new Message[] {mimeMessage});
		folder.close();
		store.close();
	}
	
	private void showAllFoldersOnServer(MailSaverProperties mailSaverProperties){
		try {
			Session session = mailSaverProperties.getSession();
			String username = mailSaverProperties.getUsername();
			String password = mailSaverProperties.getPassword();
			String storeHost = mailSaverProperties.getStoreHost();
			
			Store store = session.getStore(mailSaverProperties.getStoreProtocol());
			store.connect(storeHost, username, password);
			
			Folder folder = store.getDefaultFolder();
			Folder [] list = folder.list("*");
			System.out.println("Existing folder on server:");
			for(Folder f : list) {
				System.out.println(f.getName() + " | " + f.getFullName());
			}
			folder.close();
			store.close();
		}catch(MessagingException | IllegalStateException exception) {
			
		}
	}
	
}
