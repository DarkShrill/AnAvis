package anAvis;

import interfaces.Account;
import interfaces.AccountType;
import view.Console;

/**
 * @author edoardo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UnregisteredUser unUsr;
		Console view;
		Network<Account> network = null;
		boolean loginStatus = false; 
		String email="";
		String password="";
		AccountType accountType = AccountType.NOONE;
		Doctor doctor = null;
		Donor donor = null;
		EmergencyRoom emergency = null;
		AvisOffice avisSite = null;
		
		view = new Console();
		network = new Network<Account>(view);
		
		unUsr = new UnregisteredUser(view, network);
		

		//--------------------------------------------------------------
		
		view.goToMainView();
		
		int choice = view.getMenuChoice();

		switch(choice) {
			case 1 :
				unUsr.createAccount();
				return;
			case 2 :
				do {
					accountType = view.getAccountType();
					email = view.getEmail();
					password = view.getPassword();
					loginStatus = unUsr.loginToAccount(accountType,email,password);	
				}while(!loginStatus);
				break;
		}
		
		if(loginStatus) {
			//SONO LOGGATO
			
			if (accountType == AccountType.DOCTOR) {
				doctor = (Doctor) network.getAccountData(accountType, email);
			} else if (accountType == AccountType.DONOR) {
				donor = (Donor) network.getAccountData(accountType, email);
			} else if (accountType == AccountType.EMERGENCY_ROOM) {
				emergency = (EmergencyRoom) network.getAccountData(accountType, email);
			} else if (accountType == AccountType.AVIS_OFFICE) {
				avisSite = (AvisOffice) network.getAccountData(accountType, email);
			}
			
			choice = view.showSubMenu(accountType);
			
			switch(choice) {
				case 1 :
					if(accountType == AccountType.AVIS_OFFICE) {
						avisSite.insertAvaibleDatesAndHours();
					}
					if(accountType == AccountType.DONOR) {
						if(donor.reservationForBloodDonation()) {
						 view.success();
						} else {
							view.cannotDonate();
						}
					}
					if(accountType == AccountType.EMERGENCY_ROOM) {
						if(emergency.bloodEmergencyRequest()) {
							view.success();
						}
					}
					return;
				case 2 :
					if(accountType == AccountType.AVIS_OFFICE) {
						avisSite.modifyAvaibleDatesAndHours();
					}
					break;
			}
			
			
			
		}
		
		
		
	}

}
