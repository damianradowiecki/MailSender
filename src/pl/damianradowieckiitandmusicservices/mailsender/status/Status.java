package pl.damianradowieckiitandmusicservices.mailsender.status;

public enum Status {
	SENT_AND_SAVED_IN_FOLDER,
	SENT_BUT_NOT_SAVED_IN_FOLDER,
	NOT_SENT_BUT_SAVED_IN_FOLDER,
	NOT_SENT_AND_NOT_SAVED_IN_FOLDER;
	
	public static Status mergeStatuses(SendingStatus sendingStatus, SavingInFolderStatus savingInFolderStatus) {
		if (sendingStatus.equals(SendingStatus.SENT)) {
			if (savingInFolderStatus.equals(SavingInFolderStatus.SAVED)) {
				return Status.SENT_AND_SAVED_IN_FOLDER;
			} else {
				return Status.SENT_BUT_NOT_SAVED_IN_FOLDER;
			}
		} else {
			if (savingInFolderStatus.equals(SavingInFolderStatus.SAVED)) {
				return Status.NOT_SENT_BUT_SAVED_IN_FOLDER;
			} else {
				return Status.NOT_SENT_AND_NOT_SAVED_IN_FOLDER;
			}
		}
	}
}
