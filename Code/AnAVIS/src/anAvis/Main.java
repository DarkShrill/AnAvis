package anAvis;

import java.util.Scanner;

import interfaces.Account;
import interfaces.AccountType;
import interfaces.NetworkInterface;
import interfaces.ViewInterface;
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
		
		
		String input = "";
		boolean correctInput = false;
		UnregisteredUser unUsr;
		ViewInterface view;
		NetworkInterface<?> network = null;
		boolean loginStatus = false; 
		String email="";
		String password="";
		AccountType accountType = AccountType.NOONE;
		Doctor doctor;
		Donor donor;
		EmergencyRoom emergency;
		AvisOffice avisSite;
		
		
		view = new Console();
		unUsr = new UnregisteredUser(view, network);
	

		//--------------------------------------------------------------
		
		view.goToMainView();
		
		int choice = view.getMenuChoice();

		switch(choice) {
			case 1 :
				unUsr.createAccount();
				return;
			case 2 :
				accountType = view.getAccountType();
				email = view.getEmail();
				password = view.getPassword();
				loginStatus = unUsr.loginToAccount(accountType,email,password);
				break;
		}
		
		if(loginStatus) {
			//SONO LOGGATO
			
			if (accountType == AccountType.DOCTOR) {
				doctor = new Doctor(email,password, new Object());
			} else if (accountType == AccountType.DONOR) {
				donor = new Donor(network.getNameFromEmail(email),network.getSurnameFromEmail(email),email,password,view.getBloodGroup(),
						network.getEnableToEmergencyRequest(email),network,view, network.getResidence(), network.getGender());
			} else if (accountType == AccountType.EMERGENCY_ROOM) {
				emergency = new EmergencyRoom(email, password, network.getSite(), view, network);
			} else if (accountType == AccountType.AVIS_OFFICE) {
				avisSite = new AvisOffice(email, password, network.getSite(),network,view);
			}
			
			choice = view.showSubMenu(accountType);
			
			switch(choice) {
				case 1 :
					if(accountType == AccountType.AVIS_OFFICE) {
						avisSite.insertAvaibleDatesAndHours();
					}
					if(accountType == AccountType.DONOR) {
						donor.reservationForBloodDonation();	
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
