/**
 * 
 */
package view;

import java.util.List;

import anAvis.AvaiableDateAndHours;
import interfaces.AccountType;
import interfaces.ViewInterface;

/**
 * @author edoardo
 *
 */
public class Console implements ViewInterface {

	@Override
	public AccountType getAccountType() {
		boolean correctInput = false;
		AccountType type = AccountType.NOONE;
		
		System.out.println("####  Perfavore inserisci il tipo di account che vuoi creare:      (Esempio : \"DONOR\")");
		System.out.println("");
		System.out.println("####  - DONOR");
		System.out.println("####  - DOCTOR");
		System.out.println("####  - EMERGENCYROOM");
		System.out.println("####  - AVISOFFICE");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			String input = System.console().readLine();
			
			if(input.compareTo("DONOR") == 0) {
				type = AccountType.DONOR;
				correctInput = true;
			}
			else if(input.compareTo("DOCTOR") == 0) {
				type = AccountType.DOCTOR;
				correctInput = true;
			}
			else if(input.compareTo("EMERGENCYROOM") == 0) {
				type = AccountType.EMERGENCY_ROOM;
				correctInput = true;
			}
			else if(input.compareTo("AVISOFFICE") == 0) {
				type = AccountType.AVIS_OFFICE;
				correctInput = true;
			}
			else {
				System.out.println("\n \n ####  Perfavore, inserisci un account corretto! ");
			}
		}while(!correctInput);
		
		
		return type;
	}

	@Override
	public Object getDocument() {
		System.out.println("####  Perfavore inserisci la cartificazione:");
		System.out.println("");
		return new Object();
	}

	@Override
	public String getBloodGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getEnableToEmergencyRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getConfirmation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResidence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char getGender() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printList(List<?> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getAvisOffice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectAvisOfficeDates(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doYouWantAddNewAvaiableDate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAvaiableDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAvaiableHours() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void goToMainView() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSelectedAvaiableDateAndHour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDateOrHours() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showRepeatOperationMessage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showCredentialsErrorMessage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMessageForInsertAvaiableDatesAndHours() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMessageForInsertCorrectHour() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showListAvaiableDateAndHour(List<AvaiableDateAndHours> listAvaiableDateAndHour) {
		// TODO Auto-generated method stub

	}

}
