package pl.itandmusic.mailsaver;

import java.util.ArrayList;
import java.util.List;

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
		store.close();
	}
	
	private void showAllFoldersOnServer(MailSaverProperties mailSaverProperties){
		List<Folder> folders = new ArrayList<>();
		try {
			Session session = mailSaverProperties.getSession();
			String username = mailSaverProperties.getUsername();
			String password = mailSaverProperties.getPassword();
			String storeHost = mailSaverProperties.getStoreHost();
			
			Store store = session.getStore(mailSaverProperties.getStoreProtocol());
			store.connect(storeHost, username, password);
			
			Folder folder = store.getDefaultFolder();
			Folder [] list = folder.list("*");
			for(Folder f : list) {
				folders.add(f);
			}
			folder.close();
			store.close();
		}catch(MessagingException | IllegalStateException exception) {}
		
		if(folders.size() > 0) {
			System.out.println("Existing folders on server:");
			for(Folder f : folders) {
				System.out.println(f.getFullName());
			}
		}
	}
	
}
