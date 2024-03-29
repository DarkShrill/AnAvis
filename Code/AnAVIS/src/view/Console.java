/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import anAvis.AvaiableDateAndHours;
import anAvis.Pair;
import interfaces.AccountType;
import interfaces.ViewInterface;

/**
 * @author edoardo
 *
 */
public class Console implements ViewInterface {

	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private Scanner scanner = new Scanner(System.in); 
	
	@Override
	public AccountType getAccountType() {
		boolean correctInput = false;
		AccountType type = AccountType.NOONE;
		
		
		
		System.out.println("============================================================================================");
		System.out.println("|  Per favore inserisci il tipo di account:       (Esempio : \"DONATORE\")                 |");
		System.out.println("============================================================================================");
		System.out.println("|               Opzioni :                                                                  |");
		System.out.println("|                   - DONATORE                                                             |");
		System.out.println("|                   - DOTTORE                                                              |");
		System.out.println("|                   - PRONTO SOCCORSO                                                      |");
		System.out.println("|                   - SEDE AVIS                                                            |");
		System.out.println("============================================================================================");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			String input = scanner.nextLine().toUpperCase();
			
			if(input.compareTo("DONATORE") == 0) {
				type = AccountType.DONOR;
				correctInput = true;
			}
			else if(input.compareTo("DOTTORE") == 0) {
				type = AccountType.DOCTOR;
				correctInput = true;
			}
			else if(input.compareTo("PRONTO SOCCORSO") == 0) {
				type = AccountType.EMERGENCY_ROOM;
				correctInput = true;
			}
			else if(input.compareTo("SEDE AVIS") == 0) {
				type = AccountType.AVIS_OFFICE;
				correctInput = true;
			}
			else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
			}
		}while(!correctInput);
		
		
		
		return type;
	}

	@Override
	public Object getDocument() {
		System.out.println("####  Per favore inserisci la cartificazione:");
		System.out.println("");
		return new Object();
	}

	@Override
	public String getBloodGroup() {
		
		boolean correctInput = false;
		String bloodGroup = "";
		
		int input = -1;
		
		System.out.println("=========================================================================================================================");
		System.out.println("| Per favore inserisci il gruppo sanguigno di appartenenza tra i seguenti: (indicando il gruppo con il numero associato)|");
		System.out.println("=========================================================================================================================");
		System.out.println("|                         Opzioni :                                                                                     |");
		System.out.println("|                              - 1) 0+                                                                                  |");
		System.out.println("|                              - 2) A+                                                                                  |");
		System.out.println("|                              - 3) B+                                                                                  |");
		System.out.println("|                              - 4) AB+                                                                                 |");
		System.out.println("|                              - 5) 0-                                                                                  |");
		System.out.println("|                              - 6) A-                                                                                  |");
		System.out.println("|                              - 7) B-                                                                                  |");
		System.out.println("|                              - 8) AB-                                                                                 |");
		System.out.println("=========================================================================================================================");
		
		System.out.println("");
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			
			
			try{
				int iCheck = Integer.parseInt(scanner.nextLine());
				input = iCheck;
				correctInput = true;
			}
			catch(NumberFormatException e) { correctInput = false; }

			
			switch (input) {
			case 1:
				bloodGroup = "0+";
				correctInput = true;
				break;
			case 2:
				bloodGroup = "A+";
				correctInput = true;
				break;
			case 3:
				bloodGroup = "B+";
				correctInput = true;
				break;
			case 4:
				bloodGroup = "AB+";
				correctInput = true;
				break;
			case 5:
				bloodGroup = "0-";
				correctInput = true;
				break;
			case 6:
				bloodGroup = "A-";
				correctInput = true;
				break;
			case 7:
				bloodGroup = "B-";
				correctInput = true;
				break;
			case 8:
				bloodGroup = "AB-";
				correctInput = true;
				break;

			default:
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
				break;
			}

		}while(!correctInput);
		
		
		
		return bloodGroup;
	}

	@Override
	public boolean getEnableToEmergencyRequest() {
		boolean correctInput = false;
		boolean enable = false;
		String input = "";
		
		
		System.out.println("####  Acconsenti a ricevere chiamate/messaggi nel caso in cui il pronto soccorso abbia una CARENZA SANGUE ?");
		System.out.println("####  		Per favore rispondi \"" + ANSI_GREEN + "SI" + ANSI_RESET +"\" oppure \"" + ANSI_RED + "NO" + ANSI_RESET+"\"");
		System.out.println("");
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = (scanner.nextLine().toUpperCase());
			
			if(input.compareTo("SI") == 0) {
				correctInput = true;
			}else if(input.compareTo("NO") == 0) {
				correctInput = true;
			}else {
				correctInput= false;
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
			}


		}while(!correctInput);
		
		enable = input.compareTo("SI") == 0 ? true : false;
		
		
		
		return enable;
	}

	@Override
	public String getSite() {
		boolean correctInput = false;
		String input = "";
		
		
		System.out.println("####  Per favore, inserisci il nome della sede: ");
		System.out.println("");
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = (scanner.nextLine().toUpperCase());
			
			if(input.compareTo("") == 0) {
				correctInput = false;
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
			}else {
				correctInput = true;
			}
			

		}while(!correctInput);
		
		
		
		return input;
	}

	@Override
	public boolean getConfirmation() {
		boolean correctInput = false;
		boolean enable = false;
		String input = "";
		
		
		System.out.println("####  Vuoi davvero confermare ?");
		System.out.println("####  		Per favore rispondi \"" + ANSI_GREEN + "SI" + ANSI_RESET +"\" oppure \"" + ANSI_RED + "NO" + ANSI_RESET+"\"");
		System.out.println("");
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = (scanner.nextLine().toUpperCase());
			
			if(input.compareTo("SI") == 0) {
				correctInput = true;
			}else if(input.compareTo("NO") == 0) {
				correctInput = true;
			}else {
				correctInput= false;
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
			}


		}while(!correctInput);
		
		enable = input.compareTo("SI") == 0 ? true : false;
		
		
		
		return enable;
	}

	@Override
	public String getEmail() {
		boolean correctInput = false;
		String email = "";
		String input="";
		
		
		System.out.println("####  Per favore inserisci l'email:      (Esempio : \"esempio@host.it\")");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			
			input = scanner.nextLine();
			
			if(input.compareTo("") != 0) {
				correctInput = true;
				email = input;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}
		}while(!correctInput);
		
		
		
		return email;
	}

	@Override
	public String getPassword() {
		boolean correctInput = false;
		String password = "";
		
		
		System.out.println("####  Per favore inserisci la password:      (Esempio : \"dario04\")");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			password = scanner.nextLine();

			
			if(password.compareTo("") != 0) {
				correctInput = true;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}
		}while(!correctInput);
		
		
		
		return password;
	}

	@Override
	public String getResidence() {
		boolean correctInput = false;
		String residence = "";
		
		
		System.out.println("####  Per favore inserisci la residenza:      (Esempio : \"Civitanova\")");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua citt� : ");
			String input = scanner.nextLine();
			
			if(input.compareTo("") != 0) {
				correctInput = true;
				residence = input;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}
		}while(!correctInput);
		
		
		
		return residence;
	}

	@Override
	public char getGender() {
		boolean correctInput = false;
		char sesso = ' ';
		String input = "";
		
		
		System.out.println("####  Per favore, inserisci il sesso: ?");
		System.out.println("####  		Per favore rispondi \"" + ANSI_BLUE + "M"+ ANSI_RESET +"\" se si vuole sceglie maschio, oppure \"" + ANSI_PURPLE + "F" + ANSI_RESET + "\" se si vuole scegliere femmina");
		System.out.println("");
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = (scanner.nextLine().toUpperCase());
			
			if(input.compareTo("M") == 0) {
				correctInput = true;
			}else if(input.compareTo("F") == 0) {
				correctInput = true;
			}else {
				correctInput= false;
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
			}


		}while(!correctInput);
		
		sesso = input.compareTo("F") == 0 ? 'F' : 'M';
		
		
		
		return sesso;
	}

	@Override
	public void printList(List<?> list) {
		System.out.println("####  LISTA : ");
		System.out.println("");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(" " + i + ")  " + list.get(i));
		}
		
	}

	@Override
	public int getAvisOffice() {
		boolean correctInput = false;
		String input = "";
		
		
		System.out.println("####  Per favore inserisci la sede avis:");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine();

			
			if(input.compareTo("") != 0) {
				correctInput = true;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}
		}while(!correctInput);
		
		
		
		return Integer.parseInt(input);
	}

	@Override
	public boolean doYouWantAddNewAvaiableDate() {
		boolean correctInput = false;
		boolean enable = false;
		
		String input = "";
		
		System.out.println("####  Vuoi aggiungere una nuova data ?");
		System.out.println("####  		Per favore rispondi \"" + ANSI_GREEN + "SI" + ANSI_RESET +"\" oppure \"" + ANSI_RED + "NO" + ANSI_RESET+"\"");
		System.out.println("");
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = (scanner.nextLine().toUpperCase());
			
			if(input.compareTo("SI") == 0) {
				correctInput = true;
			}else if(input.compareTo("NO") == 0) {
				correctInput = true;
			}else {
				correctInput= false;
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
			}


		}while(!correctInput);
		
		enable = input.compareTo("SI") == 0 ? true : false;
		
		
		
		return enable;
	}
	
	@Override
	public String getAvaiableDate() {
		boolean correctInput = false;
		String input = "";
		
		System.out.println("####  Per favore, inserisci la data che si desidera:      ");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine();
			
			if(input.compareTo("") == 0) {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}else {
				correctInput = true;
			}
		}while(!correctInput);
		
		
		return input;
	}

	@Override
	public List<String> getAvaiableHours() {
		boolean correctInput = false;
		String input = "";
		
		
		System.out.println("####  Per favore, inserisci l'orario che si desidera:     (Si prega di inserire orari multipli"
				+ " \n 		nella seguente maniera: HH:MM,HH:MM,HH:MM ecc.) ");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine();
			
			if(input.compareTo("") == 0) {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}else {
				correctInput = true;
			}
		}while(!correctInput);
		
		String[] strList = input.split(",");
		
		List<String> list = new ArrayList<String>();

		for (String s : strList) {
			list.add(s);
		}
		
		return list;
	}

	@Override
	public Pair<Integer, String> getAvaiableDate(List<AvaiableDateAndHours> list, boolean isDate) {
		boolean correctInput = false;
		
		int input = -1;
		String newDate = "";
		int counter = 0;
		
		for(AvaiableDateAndHours item : list) {
			System.out.println( counter + ") " + item.getDate());
			counter++;
		}
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			try {
				input = scanner.nextInt();
				if(input < 0 || input > counter) {
					System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
					correctInput = false;
				}else {
					correctInput = true;
				}
			} catch(InputMismatchException e) {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}
		}while(!correctInput);
		
		if(!isDate) {
			
			return new Pair<Integer, String>(input, "");
		}
		
		do {
			System.out.println("#### Inserisci la nuova data : ");
			newDate = scanner.nextLine();
			
			if(newDate.equals("")) {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			} else {
				correctInput = true;
			}
		}while(!correctInput);
		
		
		return new Pair<Integer, String>(input, newDate);
	}

	@Override
	public Pair<Integer, String> getAvaiableHours(List<AvaiableDateAndHours> list, int dateIndex) {
		boolean correctInput = false;
		int input = -1;
		String newHour = "";
		
		int counter = 0;
		
		System.out.println("Data: " + list.get(dateIndex).getDate());
		for(String item : list.get(dateIndex).getHours()) {
			System.out.println( counter + ") " + item);
			counter++;
		}
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			try {
				input = scanner.nextInt();
				if(input < 0 || input > counter) {
					System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
					correctInput = false;
				}else {
					correctInput = true;
				}
			} catch(InputMismatchException e) {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}
			
			do {
				System.out.println("#### Inserisci il nuovo orario : ");
				newHour = scanner.nextLine();
				
				if(newHour.equals("")) {
					System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
					correctInput = false;
				} else {
					correctInput = true;
				}
			}while(!correctInput);
		}while(!correctInput);
		
		
		return new Pair<Integer, String>(input, newHour);
	}
	
	@Override
	public void goToMainView() {
		System.out.println("\n\n\n\n\n\n###############################################################");
		System.out.println("####                                                       ####");
		System.out.println("####                                                       ####");
		System.out.println("####                       AnAVIS HOME                     ####");
		System.out.println("####                                                       ####");
		System.out.println("####                                                       ####");
		System.out.println("###############################################################\n\n");
		
		this.printMenu();
	}

	@Override
	public int getSelectedAvaiableDateAndHour() {
		boolean correctInput = false;
		String input = "";
		
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine();

			
			if(input.compareTo("") != 0) {
				correctInput = true;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}
		}while(!correctInput);
		
		
		
		return Integer.parseInt(input);
	}

	@Override
	public String getDateOrHours() {
		boolean correctInput = false;
		
		String input = "";
		
		System.out.println("####  Per favore, scegli tra \"DATA\" o \"ORARIO\":      ");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine().toUpperCase();
			
			if(input.compareTo("DATA") == 0) {
				correctInput = true;
			}else if(input.compareTo("ORARIO") == 0) {
				correctInput = true;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}

		}while(!correctInput);
		
		return input;
	}

	@Override
	public void showRepeatOperationMessage() {
		System.out.println( ANSI_RED + "####  Qualcosa è andato storto...");
		System.out.println("###      Per favore, ripeti l'operazione! "+ ANSI_RESET);
	}
	
	@Override
	public void showNoValideCandidateFound() {
		System.out.println(ANSI_RED +"###      Nessun donatore idoneo nel raggio di 40 km "+ ANSI_RESET);
	}

	@Override
	public void showCredentialsErrorMessage() {
		System.out.println( ANSI_RED + "####  Qualcosa è andato storto, credenziali sbagliate!");
		System.out.println("###      Per favore, ripeti le credenziali! "+ ANSI_RESET);
	}

	@Override
	public void showMessageForInsertAvaiableDatesAndHours() {
		System.out.println("####  Per favore, inserisci una data e almeno un orario disponibile per una prenotazione!");
	}

	@Override
	public void showMessageForInsertCorrectDate() {
		System.out.println( ANSI_RED + "####  Qualcosa è andato storto, data sbagliata!");
		System.out.println("###      Per favore, reinserisci l'orario! "+ ANSI_RESET);
	}
	
	@Override
	public void showMessageForInsertCorrectHour() {
		System.out.println( ANSI_RED + "####  Qualcosa è andato storto, orario sbagliate!");
		System.out.println("###      Per favore, reinserisci l'orario! "+ ANSI_RESET);
	}

	@Override
	public void printMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	public void printMenu() {
		System.out.println("==============================================================");
		System.out.println("|                            MENU                            |");
		System.out.println("==============================================================");
		System.out.println("|                       1 - Crea Account                     |");
		System.out.println("|                       2 - Login                            |");
		System.out.println("==============================================================\n\n");
	}

	@Override
	public int getMenuChoice() {
		boolean correctInput = false;
		
		String input = "";
		
		do {
			System.out.println(ANSI_YELLOW + "Inserisci la tua scelta : " + ANSI_RESET);
			input = scanner.nextLine();
			
			if(input.compareTo("1") == 0) {
				correctInput = true;
			}else if(input.compareTo("2") == 0) {
				correctInput = true;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}

		}while(!correctInput);
		
		return Integer.parseInt(input);
	}

	@Override
	public int showSubMenu(AccountType accountType) {
		boolean correctInput = false;
		
		String input = "";
		
		if (accountType == AccountType.DOCTOR) {
			return -1;
		} else if (accountType == AccountType.DONOR) {
			this.donorMenu();
		} else if (accountType == AccountType.EMERGENCY_ROOM) {
			this.emergencyRoomMenu();
		} else if (accountType == AccountType.AVIS_OFFICE) {
			this.avisOfficeMenu();
		}
		
		do {
			System.out.println(ANSI_YELLOW + "Inserisci la tua scelta : " + ANSI_RESET);
			input = scanner.nextLine();
			
			if(input.compareTo("1") == 0) {
				correctInput = true;
			}else if(input.compareTo("2") == 0) {
				correctInput = true;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}

		}while(!correctInput);
		
		return Integer.parseInt(input);
	}

	@Override
	public void donorMenu() {
		System.out.println("\n\n\n==============================================================");
		System.out.println("|                         DONOR MENU                         |");
		System.out.println("==============================================================");
		System.out.println("|              1 - Prenota Donazione                         |");
		System.out.println("==============================================================\n\n");
	}

	@Override
	public void avisOfficeMenu() {
		System.out.println("==============================================================");
		System.out.println("|                      AVIS OFFICE MENU                      |");
		System.out.println("==============================================================");
		System.out.println("|              1 - Inserisci date e orari                    |");
		System.out.println("|              2 - Modifica date e orari                     |");
		System.out.println("==============================================================\n\n");
	}

	@Override
	public void emergencyRoomMenu() {
		System.out.println("==============================================================");
		System.out.println("|                       EMERGENCY MENU                       |");
		System.out.println("==============================================================");
		System.out.println("|              1 - Richiesa emergenza sangue                 |");
		System.out.println("==============================================================\n\n");
	}

	@Override
	public String selectAvisOfficeDate(List<String> list) {
		boolean correctInput = false;
		int choice = -1;
		
		String input = "";
		
		System.out.println("####  A seguire troverai tutte le date disponibili per la sede avis selezionata:      ");
		System.out.println("####  		Si prega di inserire il numero associato alla data scelta     ");
		System.out.println("");
		for(int i = 0; i < list.size(); i++) {
			System.out.println("####  " + i +" )  " + list.get(i));	
		}
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine();
			
			if(input.compareTo("") == 0) {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}else {
				correctInput = true;
				choice = Integer.parseInt(input);
				if(choice > list.size() - 1) {
					correctInput = false;
					System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				}
			}
		}while(!correctInput);
		
		return list.get(choice);
	}

	@Override
	public String selectAvisOfficeHour(List<String> list) {
		boolean correctInput = false;
		int choice = -1;
		
		String input = "";
		
		System.out.println("####  A seguire troverai tutte gli orari disponibili per la data selezionata:      ");
		System.out.println("####  		Si prega di inserire il numero associato all'orario scelt     ");
		System.out.println("");
		for(int i = 0; i < list.size(); i++) {
			System.out.println("####  " + i +" )  " + list.get(i));	
		}
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine();
			
			if(input.compareTo("") == 0) {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}else {
				correctInput = true;
				choice = Integer.parseInt(input);
				if(choice > list.size() - 1) {
					correctInput = false;
					System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				}
			}
		}while(!correctInput);
		
		return list.get(choice);
	}

	@Override
	public String getName() {
		boolean correctInput = false;
		String input = "";
		
		System.out.println("####  Per favore, inserisci un nome : ");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine();
			
			if(input.compareTo("") != 0) {
				correctInput = true;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}

		}while(!correctInput);
		
		return input;
	}

	@Override
	public String getSurname() {
		boolean correctInput = false;
		String input = "";
		
		System.out.println("####  Per favore,inserisci un cognome : ");
		System.out.println("");
		
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			input = scanner.nextLine();
			
			if(input.compareTo("") != 0) {
				correctInput = true;
			}else {
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
			}

		}while(!correctInput);
		
		return input;
	}

	@Override
	public AvaiableDateAndHours getModifyHours(AvaiableDateAndHours avaiableDateAndHours) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getBloodGroupForEmergencyRequest() {

		boolean correctInput = false;
		String bloodGroup = "";
		
		int input = -1;
		
		System.out.println("===================================================================================");
		System.out.println("|                  Inserisci il gruppo sanguigno desiderato                       |");
		System.out.println("===================================================================================");
		System.out.println("|                         Opzioni :                                               |");
		System.out.println("|                              - 1) 0+                                            |");
		System.out.println("|                              - 2) A+                                            |");
		System.out.println("|                              - 3) B+                                            |");
		System.out.println("|                              - 4) AB+                                           |");
		System.out.println("|                              - 5) 0-                                            |");
		System.out.println("|                              - 6) A-                                            |");
		System.out.println("|                              - 7) B-                                            |");
		System.out.println("|                              - 8) AB-                                           |");
		System.out.println("===================================================================================");
		
		System.out.println("");
		do {
			System.out.println("#### Inserisci la tua scelta : ");
			
			
			try{
				int iCheck = Integer.parseInt(scanner.nextLine());
				input = iCheck;
				correctInput = true;
			}
			catch(NumberFormatException e) { correctInput = false; }

			
			switch (input) {
			case 1:
				bloodGroup = "0+";
				correctInput = true;
				break;
			case 2:
				bloodGroup = "A+";
				correctInput = true;
				break;
			case 3:
				bloodGroup = "B+";
				correctInput = true;
				break;
			case 4:
				bloodGroup = "AB+";
				correctInput = true;
				break;
			case 5:
				bloodGroup = "0-";
				correctInput = true;
				break;
			case 6:
				bloodGroup = "A-";
				correctInput = true;
				break;
			case 7:
				bloodGroup = "B-";
				correctInput = true;
				break;
			case 8:
				bloodGroup = "AB-";
				correctInput = true;
				break;

			default:
				System.out.println( ANSI_RED + "####  Per favore, inserisci il campo correttamente! " + ANSI_RESET);
				correctInput = false;
				break;
			}

		}while(!correctInput);
		
		
		
		return bloodGroup;
	}
	
	public void cannotDonate() {
		System.out.println(ANSI_RED + "####  Non puoi donare " + ANSI_RESET);
	}
	
	public void success() {
		System.out.println(ANSI_GREEN + "####  Operazione eseguita con successo " + ANSI_RESET);
	}
	
	public void activationRequestSent() {
		System.out.println(ANSI_GREEN + "####  Richiesta inviata " + ANSI_RESET);
	}

}
